package com.example.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.concurrent.TimeUnit;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/4/6 11:01
 */
public class TestException {
//    int time;
//    private final LoadingCache<Long, TestDemo> demo;
//    this.demo1 = CacheBuilder.newBuilder()
//            .expireAfterWrite(duration, TimeUnit.HOURS)
//          .build(new CacheLoader<Long, TestDemo>() {
//        @Override
//        public TestDemo load(Long id) throws Exception {
//            // 查询 db
//            if (time++==0) {
//                throw new RunTimeException();
//            }
//            return result;
//        }
//    });
//
//    public static void main(String[] args) {
//        try {
//            try {
//                TestDemo testDemo = demoCache.getUnchecked(21L);
//                System.out.println("第一次查询：" + testDemo);
//            } catch (Exception e) {
//            }
//            TestDemo testDemo1 = testDemo.getUnchecked(21L);
//            System.out.println("第二次查询：" + testDemo1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}

interface TestDemo{

}

