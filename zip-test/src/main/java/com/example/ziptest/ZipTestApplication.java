package com.example.ziptest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

/**
 * 这个项目修改代码后不会重新编译
 * 修改代码后需要  mvn clean package  重新编译才能生效
 */
@SpringBootApplication
public class ZipTestApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ZipTestApplication.class, args);
        String[] beanNamesForType1 = context.getBeanNamesForType(Object.class);
        System.out.println("size=" + beanNamesForType1.length);
        String[] beanNamesForType = context.getBeanNamesForType(Controller.class);
        System.out.println("controller size=" + beanNamesForType.length);
        for (String s : beanNamesForType) {
            System.out.println(s);
        }
    }

}
