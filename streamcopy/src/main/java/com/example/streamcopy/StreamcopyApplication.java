package com.example.streamcopy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StreamUtils;

import java.io.*;
/*
StreamUtils 用法
 */
@SpringBootApplication
public class StreamcopyApplication {

    public static void main(String[] args) {
        copyBytesToFile();
        SpringApplication.run(StreamcopyApplication.class, args);
    }

    // 拷贝文件
    static void copyFile(){
        try{
            String inputFileName = "/Users/demussong/Desktop/local/shuiyin.pdf";
            String outputFileName = "/Users/demussong/Desktop/local/shuiyin2.pdf";
            File outputFile = new File(outputFileName);
            InputStream in = new FileInputStream(inputFileName);
            OutputStream out = new FileOutputStream(outputFile);

            StreamUtils.copy(in, out);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void copyBytesToFile() {
       try {
           String outputFileName = "/Users/demussong/Desktop/local/test2.txt";
           String string = "Should be copied to OutputStream.";
           byte[] byteArray = string.getBytes();
           OutputStream out = new FileOutputStream(outputFileName);

           StreamUtils.copy(byteArray, out);
       }catch (Exception e) {
           e.printStackTrace();
       }
    }

}
