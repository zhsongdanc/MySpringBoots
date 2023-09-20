package com.example.redistest;

import javax.naming.ldap.Control;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

@SpringBootApplication
public class RedisTestApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(RedisTestApplication.class, args);

        String[] beanNamesForType = context.getBeanNamesForType(Controller.class);
        for (String s : beanNamesForType) {
            System.out.println(s);
        }
    }




}
