package com.example.task.test;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/1/11 16:56
 */
public class PrintTest {

    public static void main(String[] args) {
        Object lock = new Object();
        System.out.println("abc");
        ThreadA threadA = new ThreadA(lock, 1);
        ThreadB threadB = new ThreadB(lock, 1);
        threadA.setName("threadA");
        threadB.setName("threadB");
        threadA.start();
        threadB.start();

    }
}

