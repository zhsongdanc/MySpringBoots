package com.example.java8;

import java.io.File;
import java.net.URL;
import java.util.Enumeration;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogApplication {

    public static void main(String[] args) throws Exception{
        getAllFromClassPath();
//        logger.info("验证slf4j");
        SpringApplication.run(LogApplication.class, args);
//        logger.info("wefwwe");
    }

//    static final Logger logger = LoggerFactory.getLogger(LogApplication.class);


    static void getAllFromClassPath() throws Exception{
        Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources("");
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            File root = new File(url.getPath());
            for (File file : Objects.requireNonNull(root.listFiles())) {
                if (file.isDirectory()) {
                    // Loop through its listFiles() recursively.
                } else {
                    String name = file.getName();
                    System.out.println(name);
                    // Check if it's a .class file or a .jar file and handle accordingly.
                }
            }
        }
    }
}
