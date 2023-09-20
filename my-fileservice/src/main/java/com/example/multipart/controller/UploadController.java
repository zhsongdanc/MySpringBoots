package com.example.multipart.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2022/9/13 09:56
 */
@RestController
public class UploadController {

    /**
     * @RequestPart("file")  可以改变变量的名字，或者不使用该注解
     */
    @RequestMapping("/upload2")
    public String upLoad(MultipartFile multipartFile){
        System.out.println("文件上传开始");
        System.out.println("文件{}" + multipartFile.getOriginalFilename());

        if (!multipartFile.isEmpty()){
            try {
                //上传的文件需要保存的路径和文件名称，路径需要存在，否则报错
                multipartFile.transferTo(new File("/Users/demussong/test/" + multipartFile.getOriginalFilename()));
            } catch (IllegalStateException | IOException e){
                e.printStackTrace();
                return "上传失败";
            }
        } else {
            return "请上传文件";
        }
        return "上传成功";
    }




}