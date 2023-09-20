package com.example.myfilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class MyfilterApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyfilterApplication.class, args);
    }

}
