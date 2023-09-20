package com.example.asynctest.util;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2022/12/29 10:56
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * SmsUtil
 *
 * @author wliduo[i@dolyw.com]
 * @date 2020/5/20 10:50
 */
@Component
public class SmsUtil {

    private static final Logger logger = LoggerFactory.getLogger(SmsUtil.class);

    /**
     * 异步发送短信
     *
     * @param phone
     * @param code
     * @return void
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2020/5/20 10:53
     */
    @Async
    public void sendCode(String phone, String code) {
        logger.info("开始发送验证码...");
        // 模拟调用接口发验证码的耗时
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("发送成功: {}", phone);
    }

}
