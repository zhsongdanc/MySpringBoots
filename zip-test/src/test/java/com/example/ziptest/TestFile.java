package com.example.ziptest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2022/10/8 15:12
 */
@SpringBootTest
public class TestFile {
    public static void main(String[] args) {
        StringBuilder resultMsgBuilder = new StringBuilder("本次下载详情:").append("\r\n");
        resultMsgBuilder.append("hello!");
        System.out.println(resultMsgBuilder.toString());
    }

    @Test
    void createDir() throws IOException {
        Path dirPath = Paths.get("/Users/demussong/temp/", "txt");
        Files.createDirectories(dirPath);
        System.out.println(dirPath.toString());
    }
}
