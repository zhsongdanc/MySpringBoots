package com.example.springtest.controller;


import com.example.springtest.dto.TestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2022/10/31 17:00
 */
@RestController
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @PostMapping("/test")
    public String test(@RequestBody TestDTO dto) {
        System.out.println("test");
//        log.info(dto.getName());
        try {
            Thread.sleep(1000*65);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }
}
