package com.demus.autoconfig.controller;

import com.demus.autoconfig.annotation.Validate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/5/12 18:04
 */
@RestController
public class MyController1 {

    @GetMapping("/hello")
    @Validate
    public String sayHello() {
        return "Hello";
    }
}
