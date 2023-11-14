package com.example.future;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2022/12/7 09:53
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

/**
 * The unit test for ListenableFuture/CompletableFuture.
 * Created by yiqun01.lin
 * on 2018/5/3.
 */
public class TestFutures {
    //线程池中线程个数
    private static final int POOL_SIZE = 50;
    //带有回调机制的线程池
    private static final ListeningExecutorService service = MoreExecutors
            .listeningDecorator(Executors.newFixedThreadPool(POOL_SIZE));

    private static Logger LOG = LoggerFactory.getLogger(TestFutures.class);

    @Test
    public void testListenableFuture() {}



    /**
     *     缺点：
     *     1. get会导致阻塞，无法异步执行
     *     2. 执行任务中抛出异常，只能抛出给调用线程，不支持更高级的处理方式
     *     3. 只能获得一个任务的执行结果
     *     4. 不能自定义任务之间的依赖关系
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testJdkFuture() throws ExecutionException, InterruptedException {


        Callable<Integer> callable = () -> Integer.parseInt("1");

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> result = executorService.submit(callable);
        Integer integer = result.get();
        System.out.println(integer);

    }


}
