package com.example.multipart.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2022/9/13 09:56
 */
@RestController
public class FastdfsUploadController {
    private static Logger logger = LoggerFactory.getLogger(FastdfsUploadController.class);

    /**
     * @RequestPart("file")  可以改变变量的名字，或者不使用该注解
     */
    @PostMapping("/uploadFast")
    public String upLoad(@RequestPart MultipartFile multipartFile){
//        System.out.println("文件上传开始");
//        System.out.println("文件{}" + multipartFile.getOriginalFilename());
//
//        if (!multipartFile.isEmpty()){
//            try {
//                //上传的文件需要保存的路径和文件名称，路径需要存在，否则报错
//                multipartFile.transferTo(new File("/Users/demussong/test/" + multipartFile.getOriginalFilename()));
//            } catch (IllegalStateException | IOException e){
//                e.printStackTrace();
//                return "上传失败";
//            }
//        } else {
//            return "请上传文件";
//        }
//        return "上传成功";
        return upload(multipartFile,"/dir1");
    }


    public String upload(MultipartFile file, String dir) {



        OkHttpClient httpClient =  new OkHttpClient();

//        httpClient.setConnectTimeout(60 * 1000, TimeUnit.MILLISECONDS);
//        httpClient.setReadTimeout(5 * 60 * 1000, TimeUnit.MILLISECONDS);
//        httpClient.setWriteTimeout(5 * 60 * 1000, TimeUnit.MILLISECONDS);

        try {
            if (file.isEmpty()) {
                return ("不能上传空文件!!!");
            }

            long totalSize = file.getSize();


            //拆分1M小文件
            int eachSize =  1024 * 1024 * 3;
            int fileNumber = (int) (totalSize % eachSize == 0 ? totalSize / eachSize : totalSize / eachSize + 1);
            //针对小于20M不用分片
            byte[] totalBytes = file.getBytes();
            logger.info("upload ....");

            if (1 == fileNumber) {
                logger.info("上传文件中 ......");
                long start =System.currentTimeMillis();
                RequestBody filebody = RequestBody.create(MediaType.parse("application/octet-stream"), totalBytes, 0, (int) totalSize);
                MultipartBody multipartBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("file", file.getOriginalFilename(), filebody)
                        .addFormDataPart("encryptMode","privately")
                        .addFormDataPart("dir",dir!=null?dir:"").build();


                Request request = new Request.Builder().url("http://localhost:8098/v2/fileUpload").headers(null).post(multipartBody).build();
                Response response = httpClient.newCall(request).execute();
                long end =System.currentTimeMillis();
                logger.info("upload file response:{} ", JSONObject.toJSONString(response));
                if (response.isSuccessful()) {
                    logger.info("dir:{},fileName:{},总耗时:{}ms",dir,file.getOriginalFilename(),end-start);
                    String json = response.body().string();
                    logger.info("upload file response:{} ", json);
                    String uuid = JSON.parseObject(json).getJSONObject("data").getString("uuid");

                    FileVo fileVo = new FileVo();
                    fileVo.setFileName(file.getOriginalFilename());
                    fileVo.setFileSize(file.getSize());
                    fileVo.setFileUuid(uuid);
//                    fileVo.setFilePreviewUrl(previewUrl+ uuid );
                    return fileVo.toString();
                }
            } else {
                logger.info("大于20M, 开始分片上传 ......");
                String uuid = null;
                String partUploadId = null;
                for (int i = 0; i < fileNumber; i++) {
                    System.out.println(dir);
                    if (i != fileNumber - 1) {
                        long start =System.currentTimeMillis();
                        //分片上传
                        //第一片
                        if (i == 0) {
                            RequestBody filebody = RequestBody.create(MediaType.parse("application/octet-stream"), totalBytes, 0, eachSize);
                            MultipartBody multipartBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                                    .addFormDataPart("file", file.getOriginalFilename(), filebody)
                                    .addFormDataPart("uploadMode", "append")
                                    .addFormDataPart("encryptMode","privately").build();
                            Request request = new Request.Builder().url("http://localhost:8098/v2/fileUpload").headers(getHeaders()).post(multipartBody).build();
                            Response response = httpClient.newCall(request).execute();
                            if (response.isSuccessful()) {
                                String json = response.body().string();
                                uuid = JSON.parseObject(json).getJSONObject("data").getString("uuid");
//                                partUploadId = JSON.parseObject(json).getJSONObject("data").getString("partUploadId");
                                logger.info("upload file response:{} ", json);
                            }else {
                                return ("上传附件失败");
                            }
                        } else {
                            RequestBody filebody = RequestBody.create(MediaType.parse("application/octet-stream"), totalBytes, eachSize * i, eachSize);
                            MultipartBody multipartBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                                    .addFormDataPart("file", file.getOriginalFilename(), filebody)
                                    .addFormDataPart("fileUuid", uuid)
//                                    .addFormDataPart("partUploadId", partUploadId)
                                    .addFormDataPart("uploadMode", "append")
                                    .addFormDataPart("encryptMode","privately").build();
                            Request request = new Request.Builder().url("http://localhost:8098/v2/fileUpload").headers(getHeaders()).post(multipartBody).build();
                            Response response = httpClient.newCall(request).execute();
                            logger.info("upload file response:{} ", response.body().string());
                            if (!response.isSuccessful()){
                                return ("上传附件失败");
                            }
                        }
                        long end =System.currentTimeMillis();
                        logger.info("dir:{},fileName:{},step:{},总耗时:{}ms",dir,file.getOriginalFilename(),i+1,end-start);
                    } else {
                        long start1 =System.currentTimeMillis();
                        //最后一块分片
                        RequestBody filebody = RequestBody.create(MediaType.parse("application/octet-stream"), totalBytes, eachSize * i, (int) (totalSize - (eachSize * i)));
                        MultipartBody multipartBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                                .addFormDataPart("uploadMode", "total")
                                .addFormDataPart("file", file.getOriginalFilename(), filebody)
                                .addFormDataPart("fileUuid", uuid)
//                                .addFormDataPart("partUploadId", partUploadId)
                                .addFormDataPart("encryptMode","privately")
                                .addFormDataPart("dir",null!=dir?dir:"").build();
                        Request request = new Request.Builder().url("http://localhost:8098/v2/fileUpload").headers(getHeaders()).post(multipartBody).build();
                        Response response = httpClient.newCall(request).execute();
                        if (response.isSuccessful()) {
                            String json = response.body().string();
                            logger.info("upload file response:{} ", json);
                            long end1 =System.currentTimeMillis();
                            logger.info("dir:{},fileName:{},step:{},总耗时:{}ms",dir,file.getOriginalFilename(),i+1,end1-start1);
                            FileVo fileVo = new FileVo();
                            fileVo.setFileName(file.getOriginalFilename());
                            fileVo.setFileSize(file.getSize());
                            fileVo.setFileUuid(uuid);
//                            fileVo.setFilePreviewUrl(previewUrl+ uuid );
                            return fileVo.toString();
                        }
                    }
                }
            }
        } catch (IOException e) {
            logger.error("file upload error:{}", e);
            return "上传附件失败:" + e.getMessage();
        }

        return ("上传附件失败");
    }


    private Headers getHeaders() {
        Headers headers =  new Headers.Builder().add("hrgw-appname", "dos")
                .add("hrgw-timestamp","1661431878834")
                .add("hrgw-signature", "ec10b4c37097f6b73c2d02cc83a474328d6981e53208d05c5eb41593eef8e548")
                .add("staffid", "staffid")
                .add("staffname", "yiwuhe")
                .add("hrgw-channel", "esb")
                .build();

        return headers;
    }

}
