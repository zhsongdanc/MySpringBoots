package com.example.java8.service;

import com.example.java8.dao.OrderRepository;
import com.example.java8.entity.GoodsOrder;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/4/11 16:31
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<GoodsOrder> queryAll() {
        return orderRepository.findAll();
    }

    public GoodsOrder saveOne() {
        GoodsOrder goodsOrder1 = new GoodsOrder();
        goodsOrder1.setOrderId("043257852");
        goodsOrder1.setGoodsName("laptop");
        goodsOrder1.setPeopleName("demus");
        return orderRepository.save(goodsOrder1);
    }

    public void buySomething() {
        // 1. 执行半个事物

        // 2。 发消息


        // 3。正常提交或回滚
    }
}
