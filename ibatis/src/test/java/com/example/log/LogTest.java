package com.example.log;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/4/4 15:59
 */
public class LogTest {

    static final Logger logger = LoggerFactory.getLogger(LogTest.class);

    public static void main(String[] args) {
        logger.info("验证slf4j");
    }
}
