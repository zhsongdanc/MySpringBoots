package com.example.redistest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/9/5 14:14
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "hello";
    }
}
