package com.example.springtest.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/4/13 10:41
 */
@Service
public class CacheService {

    @Cacheable(value = "cache1")
    public String test() {
        System.out.println("hello");
        return "af;we";
    }
}
