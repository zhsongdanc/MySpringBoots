package com.demus.cglib.service;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/1/10 16:38
 */
public class HelloImpl implements IHello {
    @Override
    public void sayHello() {
        System.out.println("hello");
    }
}
