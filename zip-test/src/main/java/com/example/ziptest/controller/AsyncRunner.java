package com.example.ziptest.controller;

import java.net.InetAddress;
import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AsyncRunner extends Thread {

    private static Logger logger = LoggerFactory.getLogger(AsyncRunner.class);

    @PostConstruct
    public void init() {

//        this.setName("BatchFileAsyncRunner");
        this.start();
    }

    private volatile Boolean runContinue = true;
    private Object lock = new Object();

    @Override
    public void run() {
        while (runContinue) {
            try {

                synchronized (lock) {
                    logger.info("current thread is:{}",Thread.currentThread().getName());
                    lock.wait(5 * 1000);
                }


            } catch (Exception e) {
                logger.error("run failed,error:{}",e);
            }
        }
    }
}
