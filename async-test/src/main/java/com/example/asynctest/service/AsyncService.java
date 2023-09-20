package com.example.asynctest.service;

import java.util.concurrent.Future;
import org.springframework.scheduling.annotation.Async;

public interface AsyncService {


    void task1() throws Exception;


    Future<String> task2() throws Exception;


    Future<String> task3() throws Exception;


    Future<String> task4() throws Exception;
}
