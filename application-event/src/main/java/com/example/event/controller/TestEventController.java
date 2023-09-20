package com.example.event.controller;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2022/9/23 19:00
 */
@RestController
public class TestEventController implements ApplicationContextAware {

    @Autowired
    public ApplicationContext applicationContext;

    @GetMapping("/test")
    public String test() {
        applicationContext.publishEvent(new ApplicationEvent(new String("自己发布的事件")) {

        });
//        applicationContext.clo
        return "ok";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
