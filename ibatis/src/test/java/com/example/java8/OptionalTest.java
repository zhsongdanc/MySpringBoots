package com.example.java8;

import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
 * @Author: demussong
 * @Description:  参考：https://www.baeldung.com/java-difference-map-and-flatmap
 * @Date: 2022/12/6 18:44
 */
public class OptionalTest {

    @Test
    void testMap(){
        Optional<String> s = Optional.of("test");
        Assertions.assertEquals(Optional.of("TEST"), s.map(String::toUpperCase));
    }

    @Test
    void test2(){
        Assertions.assertEquals(Optional.of(Optional.of("STRING")),
                Optional
                        .of("string")
                        .map(s -> Optional.of("STRING")));
    }

    @Test
    void test3(){
        Assertions.assertEquals(Optional.of("STRING"), Optional
                .of("string")
                // 将string作为s
                .flatMap(s -> Optional.of("STRING")));
    }

    @Test
    void test4(){
        String str = null;
        Optional<String> opt = Optional.of(str);
        Optional<String> opt2 = Optional.ofNullable(str);
    }
}

