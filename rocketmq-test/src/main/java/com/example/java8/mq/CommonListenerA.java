package com.example.java8.mq;

import com.example.java8.entity.MqMessage;
import com.example.java8.util.MqUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/4/11 19:45
 */
@Slf4j
@Component
@RocketMQMessageListener(
        topic = MqUtil.common_topic,
        consumerGroup = "common_consumer_a_group")
public class CommonListenerA implements RocketMQListener<MqMessage> {
    @Override
    public void onMessage(MqMessage message) {
        log.info("{}收到消息：{}", this.getClass().getSimpleName(), message);
    }
}
