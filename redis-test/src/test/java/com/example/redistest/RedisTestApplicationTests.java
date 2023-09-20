package com.example.redistest;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisTestApplicationTests {

    @Test
    void contextLoads() {
    }




    @Test
    public void test() {
        RedisClient redisClient = RedisClient.create("redis://123456@localhost:6379/0");
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> syncCommands = connection.sync();

        syncCommands.set("key", "Hello, Redis!");

        connection.close();
        redisClient.shutdown();
    }

}
