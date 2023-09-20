package com.demus.autoconfig.annotation;

import java.lang.reflect.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/5/12 18:02
 */
@Aspect
@Component
public class ValidationAspect {

    @Pointcut("@annotation(com.demus.autoconfig.annotation.Validate)")
    public void validatePointcut() {}

    @Before("validatePointcut()")
    public void validate(JoinPoint pjp) throws Exception {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();

        try {
            if ("sayHello".equals(methodName)) {
                throw new Exception("my exception");
            }
            int x = 3/5;
        }catch (Throwable t) {
            throw t;
        }finally {
            System.out.println("finally");
        }
//        return "abc";
        // 检验逻辑
    }
}