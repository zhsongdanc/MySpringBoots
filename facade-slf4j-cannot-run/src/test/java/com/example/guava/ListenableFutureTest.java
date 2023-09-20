package com.example.guava;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/3/22 17:36
 */
public class ListenableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        testListenFuture();
    }

    public static void testListenFuture() throws ExecutionException, InterruptedException {
        System.out.println("主线程start");
        ListeningExecutorService pool = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));

        Task task1 = new Task();
        task1.args = "task1";
        Task task2 = new Task();
        task2.args = "task2";
        ListenableFuture<String> future = pool.submit(task1);
        ListenableFuture<String> future2 = pool.submit(task2);


        future2.addListener(() -> System.out.println("addListener 不能带返回值"), pool);


//        FutureCallback<String> futureCallback = new FutureCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                System.out.println("Futures.addCallback 能带返回值：" + result);
//            }
//            @Override
//            public void onFailure(Throwable t) {
//                System.out.println("出错,业务回滚或补偿");
//            }
//        };


//        Futures.addCallback(future, futureCallback, pool);
        future.get();
        System.out.println("主线程end");
        pool.shutdownNow();
    }
}

class Task implements Callable<String> {
    String args;
    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        System.out.println("任务：" + args);
        String str = "ab";
        if ("ab".equals(str)) {
            throw new RuntimeException("123");
        }
        return "dong";
    }
}

