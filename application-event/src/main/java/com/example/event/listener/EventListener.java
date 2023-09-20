package com.example.event.listener;

import com.example.event.event.MyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/2/1 17:35
 */
@Component
public class EventListener implements ApplicationListener<MyEvent> {

    @Override
    public void onApplicationEvent(MyEvent event) {
        String message = event.getMessage();
        System.out.println("收到的消息为：" + message);
    }
}
