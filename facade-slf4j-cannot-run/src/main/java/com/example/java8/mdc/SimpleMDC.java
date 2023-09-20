package com.example.java8.mdc;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/4/25 19:49
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import java.util.UUID;

/**
 * MDC快速入门示例
 *
 * @author 一猿小讲
 */
public class SimpleMDC {
    public static void main(String[] args) {
        new BizHandle("F0000").start();
        new BizHandle("F9999").start();
    }
}

class BizHandle extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(SimpleMDC.class);
    public static final String REQ_ID = "REQ_ID";

    private String funCode;

    public BizHandle(String funCode) {
        this.funCode = funCode;
    }

    @Override
    public void run() {
        MDC.put(REQ_ID, UUID.randomUUID().toString());
        logger.info("开始调用服务{}，进行业务处理", funCode);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            logger.info(e.getMessage());
        }
        logger.info("服务{}处理完毕，可以释放空间了，避免内存泄露", funCode);
        MDC.remove(REQ_ID);
    }
}


