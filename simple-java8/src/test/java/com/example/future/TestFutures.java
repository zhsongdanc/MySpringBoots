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
import java.util.concurrent.Executors;

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

    @Test
    public void testCompletableFuture() throws Exception {

    }
}