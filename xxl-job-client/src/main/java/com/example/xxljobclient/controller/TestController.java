package com.example.xxljobclient.controller;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2022/8/30 09:50
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "str";
    }
}
