package com.example.event.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/2/1 17:35
 */
@Component
public class MyEvent extends ApplicationEvent {

    String message;

    public MyEvent(Object source) {
        super(source);
        this.message = "This is demus!";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
