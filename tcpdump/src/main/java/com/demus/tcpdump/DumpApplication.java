package com.demus.tcpdump;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/4/27 18:45
 */

@SpringBootApplication
public class DumpApplication {

    public static void main(String[] args) {
        SpringApplication.run(DumpApplication.class, args);
    }


}
