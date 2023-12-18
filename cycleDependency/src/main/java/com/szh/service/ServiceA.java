package com.szh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/12/1 11:13
 */
@Service
public class ServiceA {

    @Autowired
    private ServiceB serviceB;
}
