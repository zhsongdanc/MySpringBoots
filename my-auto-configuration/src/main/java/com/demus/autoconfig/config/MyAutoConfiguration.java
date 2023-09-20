package com.demus.autoconfig.config;

import com.demus.autoconfig.service.MyService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/5/5 15:48
 */

@ConditionalOnClass(MyService.class)
public class MyAutoConfiguration {
    @Bean
    public MyService myService() {
        return new MyService();
    }
}
