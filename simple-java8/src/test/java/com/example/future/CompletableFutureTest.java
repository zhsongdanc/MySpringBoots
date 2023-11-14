package com.example.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/9/20 16:55
 */
public class CompletableFutureTest {


    // 1. 无阻塞

    @Test
    public void asyncTest() throws Exception {

        Runnable runnable = () -> {
            System.out.println("This is thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(runnable);

        System.out.println("mock end");
        // 这里依然会等待任务完成
        completableFuture.get();
        System.out.println("It's end");

    }

    @Test
    public void exceptionTest() throws ExecutionException, InterruptedException {
        Integer age = -1;

        CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
            if(age < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            }
            if(age > 18) {
                return "Adult";
            } else {
                return "Child";
            }
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return "Unknown!";
        });
        System.out.println("Maturity : " + maturityFuture.get());

    }

    // 更通用的方法，无论是否发生异常都会调用
    @Test
    public void exceptionTest2() throws ExecutionException, InterruptedException {
        Integer age2 = -1;

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            if(age2 < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            }
            if(age2 > 18) {
                return "Adult";
            } else {
                return "Child";
            }
        }).handle((res, ex) -> {
            if(ex != null) {
                System.out.println("Oops! We have an exception - " + ex.getMessage());
                return "Unknown!";
            }
            return res;
        });

        System.out.println("Maturity : " + future.get());
    }



    @Test
    void thenApplyTest() throws ExecutionException, InterruptedException {
        // Create a CompletableFuture
        CompletableFuture<String> whatsYourNameFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Rajeev";
        });

// Attach a callback to the Future using thenApply()
        // thenAccept() 接受参数但没返回值
        // thenRun() 不接受参数也没有返回值
        CompletableFuture<String> greetingFuture = whatsYourNameFuture.thenApply(name -> {
            return "Hello " + name;
        });

// Block and get the result of the future.
        System.out.println(greetingFuture.get());

    }


    @Test
    void allOfTest() {

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Result 1");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Result 2");
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "Result 3");

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(future1, future2, future3);

        try {
            allFutures.get(); // 等待所有 CompletableFuture 对象完成
            System.out.println("All futures completed successfully.");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

    // 任务之间的依赖关系 todo 不太清楚

    @Test
    void promiseTest() {



                CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 10);
                CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 20);

                CompletableFuture<Integer> combinedFuture = future1.thenCombine(future2, (result1, result2) -> result1 + result2);

                try {
                    int combinedResult = combinedFuture.get();
                    System.out.println("Combined Result: " + combinedResult);
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();

        }


    }

   static String getUserDetails() {
        return "szh";
   }

    static class User {
        String userId;
        double creditRating;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public double getCreditRating() {
            return creditRating;
        }

        public void setCreditRating(double creditRating) {
            this.creditRating = creditRating;
        }
    }


    // 获取所有任务执行结果



}
