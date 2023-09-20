package com.example.java8.controller;

import com.example.java8.entity.MqMessage;
import com.example.java8.util.MqUtil;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/4/11 19:44
 */
@RestController
public class RocketMqController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * @return
     */
    @GetMapping("sendMq")
    public Object sendMq() {
        MqMessage message = MqMessage.builder().name("普通消息").msg("这是普通消息").build();
        SendResult sendResult = rocketMQTemplate.syncSend(MqUtil.common_topic, message);
        return sendResult;
    }
}