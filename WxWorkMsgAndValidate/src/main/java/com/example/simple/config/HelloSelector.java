package com.example.simple.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/1/4 20:53
 */
public class HelloSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.example.simple.config.Hello"};
    }
}
