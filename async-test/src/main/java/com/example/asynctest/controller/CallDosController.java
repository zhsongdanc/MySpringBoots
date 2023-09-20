package com.example.asynctest.controller;

import com.example.asynctest.client.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/4/27 11:04
 */
@RestController
public class CallDosController {

    @Autowired
    RedisClient redisClient;

    @GetMapping("/dos")
    public String callDos(){
        redisClient.set();
        return "ok";
    }
}
