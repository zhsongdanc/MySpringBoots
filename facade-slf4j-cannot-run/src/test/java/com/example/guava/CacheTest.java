package com.example.guava;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalCause;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;

/*
 * @Author: demussong
 * @Description:   https://www.baeldung.com/guava-cache
 * @Date: 2022/12/7 10:01
 */
public class CacheTest {

    @Test
    // get/getUnchecked 如果缓存有则返回，如果没有，get会计算并存入缓存
    // getIfPresent 有则返回，无则返回null
    public void whenCacheMiss_thenValueIsComputed() {
       CacheLoader<String, String> cacheLoader = new CacheLoader<String, String>() {
           @Override
           public String load(String s) throws Exception {
               return s.toUpperCase();
           }
       };
        LoadingCache<String, String> cache = CacheBuilder.newBuilder().build(cacheLoader);

        System.out.println(cache.size());
        assertEquals(0, cache.size());

        assertEquals("HELLO", cache.getUnchecked("hello"));
        assertEquals("HELLO", cache.getUnchecked("hello"));

        System.out.println(cache.size());
        assertEquals(1, cache.size());
    }

    @Test
    public void eviction() throws ExecutionException {
        CacheLoader<String, String> cacheLoader = new CacheLoader<String, String>() {
            @Override
            public String load(String s) throws Exception {
                return s.toUpperCase();
            }
        };

        LoadingCache<String, String> cache = CacheBuilder.newBuilder().maximumSize(3).build(cacheLoader);
        cache.get("a");
        cache.get("b");
        cache.get("c");
        cache.get("d");
        System.out.println(cache.getIfPresent("a"));
    }

    @Test
    public void whenEntryIdle_thenEviction()
            throws InterruptedException {
        CacheLoader<String, String> loader;
        loader = new CacheLoader<String, String>() {
            @Override
            public String load(String key) {
                return key.toUpperCase();
            }
        };

        LoadingCache<String, String> cache;
        cache = CacheBuilder.newBuilder()
                .expireAfterAccess(200, TimeUnit.MILLISECONDS)
                .build(loader);

        cache.getUnchecked("hello");
        assertEquals(1, cache.size());

        cache.getUnchecked("hello");
        Thread.sleep(300);

        cache.getUnchecked("test");
        assertEquals(1, cache.size());
        assertNull(cache.getIfPresent("hello"));
    }

    @Test
    public void whenNullValue_thenOptional() {
        CacheLoader<String, Optional<String>> loader;
        loader = new CacheLoader<String, Optional<String>>() {
            @Override
            public Optional<String> load(String key) {
                return Optional.ofNullable(getSuffix(key));
            }
        };

        LoadingCache<String, Optional<String>> cache;
        cache = CacheBuilder.newBuilder().build(loader);

        assertEquals("txt", cache.getUnchecked("text.txt").get());
        // 存入一个Optional类型的对象，Optional中的对象值为null
        assertFalse(cache.getUnchecked("hello").isPresent());
    }
    private String getSuffix(final String str) {
        int lastIndex = str.lastIndexOf('.');
        if (lastIndex == -1) {
            return null;
        }
        return str.substring(lastIndex + 1);
    }


    @Test
    public void t() {
        CacheLoader<String, String> loader = new CacheLoader<String, String>() {
            @Override
            public String load(String s) throws Exception {
                return s.toUpperCase();
            }
        };

        RemovalListener<String, String> listener;
        listener = new RemovalListener<String, String>() {
            @Override
            public void onRemoval(RemovalNotification<String, String> notification) {
                if (notification.wasEvicted()) {
                    String cause = notification.getCause().name();
                    System.out.println("cause=" + cause);
                    assertEquals(RemovalCause.SIZE.toString(), cause);
                }
            }
        };

        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .removalListener(listener)
                .maximumSize(3)
                .build(loader);

        cache.getUnchecked("first");
        cache.getUnchecked("second");
        cache.getUnchecked("third");
        cache.getUnchecked("last");
        assertEquals(3, cache.size());

    }

    @Test
    public void testException() {
        CacheLoader<String, String> cacheLoader = new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                if (key == null) {
                    throw new RuntimeException("key=null");
                }
                return key.toUpperCase();
            }
        };
        LoadingCache<String, String> loadingCache;
        loadingCache = CacheBuilder.newBuilder()
                .build(cacheLoader);
        loadingCache.getUnchecked(null);
        assertEquals(1, loadingCache.size());
    }

}
