package com.example.task.thread;

import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/1/11 16:37
 */
@Configuration
public class MyScheduledThread extends Thread{

    private volatile boolean flag = true;
    private Object lock = new Object();

    @PostConstruct
    public void init() {
        this.start();
    }

    @Override
    public void run() {
        while (flag) {
            synchronized (lock) {
                try {
                    System.out.println("demus---当前线程为：" + Thread.currentThread().getName());
                    lock.wait(2*1000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                }
            }
        }
        super.run();
    }
}
