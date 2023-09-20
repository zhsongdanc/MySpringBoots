package com.example.simple.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/1/4 20:57
 */
@Configuration
@Import(HelloSelector.class)
public class Config {

}
