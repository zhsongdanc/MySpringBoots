package com.example.java8.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/4/24 17:05
 */
@Slf4j
@Controller
public class TestResponseController {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");

    @GetMapping("/test")
    @ResponseBody
    public void test(HttpServletResponse response, String name) throws Exception{
        response.setHeader("k1","v1");
        response.getWriter().write("hello: " + name);
        response.flushBuffer();
    }


    @PostMapping("/test2")
    @ResponseBody
    public String test2() throws Exception{

        log.info("begin time: {}", System.currentTimeMillis());
        Thread.sleep(1000 * 65);
        log.info("end time: {}", System.currentTimeMillis());
        return "hello";
    }

    public static void main(String[] args) {
        String format = simpleDateFormat.format(new Date());
        log.info("format:{}", format);
    }

    @GetMapping("/timeout2")
    @ResponseBody
    public String timeout(){
        log.info("enter request");
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("before return");
        return "timeout3";
    }
}
