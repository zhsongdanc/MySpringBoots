package com.example.simple.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/3/23 15:02
 */
@Service
public class ScheduledService {

    private static final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

    //每隔3秒执行一次
    @Scheduled(cron="0/3 * * * * ?")
    public void testCron(){
        System.out.println("cron,当前时间：" +format.format(new Date()));
    }
}
