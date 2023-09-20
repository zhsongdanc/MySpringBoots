package com.fileservices.okhttp;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2022/9/14 19:05
 */
public class OkhttpTest {
    public static void main(String[] args) throws IOException {
//
//        String imgUrl = "https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png";
//        try {
//            OkHttpClient client = new OkHttpClient();
//            Request request = new Request.Builder()
//                    //访问路径
//                    .url(imgUrl)
//                    .build();
//            Response response = null;
//            response = client.newCall(request).execute();
//            //转化成byte数组
//            byte[] bytes = response.body().bytes();
//            //本地文件夹目录（下载位置）
//            String folder = "/Users/demussong/Desktop/local";
//            //切割出图片名称==》PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png
//            String filename = "test111.png";
//            Path folderPath = Paths.get(folder);
//            boolean desk = Files.exists(folderPath);
//            if (!desk) {
//                //不存在文件夹 => 创建
//                Files.createDirectories(folderPath);
//            }
//            //完整图片路径==》D:/q/w/e/r/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png
//            Path filePath = Paths.get(folder + filename);
//            boolean exists = Files.exists(filePath, LinkOption.NOFOLLOW_LINKS);
//            if (!exists) {
//                //不存在文件 => 创建
//                Files.write(filePath, bytes, StandardOpenOption.CREATE);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        download();
    }


//        Response response = httpClient.newCall(request).execute();
//        InputStream inputStream = response.body().byteStream();
//        long len = response.body().contentLength();
//        System.out.println(len);


    private static void download() throws IOException{

        OkHttpClient httpClient = new OkHttpClient();

        httpClient.setConnectTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        httpClient.setReadTimeout(5 * 60 * 1000, TimeUnit.MILLISECONDS);
        httpClient.setWriteTimeout(5 * 60 * 1000, TimeUnit.MILLISECONDS);

        Headers headers =  new Headers.Builder().add("hrgw-appname", "dos")
                .add("hrgw-timestamp","1661431878834")
                .add("hrgw-signature", "ec10b4c37097f6b73c2d02cc83a474328d6981e53208d05c5eb41593eef8e548")
                .add("x-forwarded-prefix", "/api/esb")
                .add("staffid", "staffid")
                .add("staffname", "yiwuhe").build();

//        String encryptToken = "c83a474328d6981e53208d05c5";


//        Headers headers1 = new Headers.Builder().build();

        Request request = new Request.Builder().url("http://localhost:8099/viewOnline?fileUuid=group1_M00/00/6F/CYZHs2MiwcyASAvcAAqIgB7-Il8357.pdf")
                .headers(headers).build();

        String folder = "/Users/demussong/Desktop/local/";
        String fileName = "test961.pdf";

        Response response = httpClient.newCall(request).execute();
        byte[] bytes = response.body().bytes();


        Path folderPath = Paths.get(folder);
        boolean desk = Files.exists(folderPath);
        if (!desk) {
            //不存在文件夹 => 创建
            Files.createDirectories(folderPath);
        }
        //完整图片路径==》D:/q/w/e/r/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png
        Path filePath = Paths.get(folder + fileName);
        boolean exists = Files.exists(filePath, LinkOption.NOFOLLOW_LINKS);
        if (!exists) {
            //不存在文件 => 创建
            Files.write(filePath, bytes, StandardOpenOption.CREATE);
        }



        // 3 异步调用
//        httpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//                if (response.isSuccessful()){
//                    // 4 文件下载
//                    downlodefile(response, dir, fileName);
//                }
//            }
//
//        });
    }


    private static void downlodefile(Response response, String url, String fileName) {
        InputStream inputStream = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream outputStream = null;
        try {
            inputStream = response.body().byteStream();
            //文件大小
            long total = response.body().contentLength();
            File file = new File(url, fileName);
            outputStream = new FileOutputStream(file);
            long sum = 0;
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
