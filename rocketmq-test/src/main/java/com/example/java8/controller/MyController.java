package com.example.java8.controller;

import com.example.java8.entity.GoodsOrder;
import com.example.java8.service.OrderService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/4/11 16:17
 */
@Slf4j
@RestController
public class MyController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/test")
    public List<GoodsOrder> queryAll() {

        return orderService.queryAll();
    }

    @RequestMapping("/test1")
    public GoodsOrder queryAll1() {

        return orderService.queryAll().get(0);
    }

    @RequestMapping("/save")
    public GoodsOrder save(){
        return orderService.saveOne();
    }
}
