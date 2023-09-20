package com.example.event.publisher;

import com.example.event.event.MyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/2/1 17:35
 */
public class EventPublisher {

    @Autowired
    ApplicationContext applicationContext;

    public void publishEvent(MyEvent myEvent) {
        applicationContext.publishEvent(myEvent);
    }

}
