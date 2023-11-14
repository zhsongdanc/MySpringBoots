package com.example.atomic;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReferenceArray;


/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/9/20 15:52
 */



public class AtomicReferenceArrayTest {
    public static void main(String[] args) {
        AtomicReferenceArray<String> array = new AtomicReferenceArray<>(10); // 创建一个长度为10的原子引用数组
        String value = array.get(0); // 获取索引为0的数组元素的值

        array.set(0, "Hello"); // 设置索引为0的数组元素的值为"Hello"
        array.compareAndSet(0, "Hello", "World"); // 如果索引为0的数组元素的值是"Hello"，则将其更新为"World"
        int length = array.length(); // 获取数组的长度
        System.out.println(array.get(0));
    }
}
