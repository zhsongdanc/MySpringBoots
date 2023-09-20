package com.demus.autoconfig.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/5/5 15:50
 */
@ConfigurationProperties(prefix = "my")
public class MyProperties {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    // getter and setter
}
