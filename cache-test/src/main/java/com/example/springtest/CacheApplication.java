package com.example.springtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 通过@Configuration创建的bean -- beanName（类名第一个字母小写）
 * 通过@Bean创建的bean -- beanName（方法名）
 *
 */
@EnableCaching
@SpringBootApplication
public class CacheApplication {

        public static void main(String[] args) {
            SpringApplication.run(CacheApplication.class, args);

        }


}
