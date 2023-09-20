package com.example.task.service;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2022/11/21 14:38
 */
//@Component
public class TestThread extends Thread{
    private volatile Boolean runContinue = true;
    private Object lock = new Object();

    @PostConstruct
    public void init() {

//        this.setName("BatchFileAsyncRunner");
        this.start();
    }


    @Override
    public void run() {
       while (runContinue){
           synchronized (lock){
               System.out.println("hello");
               try {
                   lock.wait(1000 * 3);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       }
    }
}
