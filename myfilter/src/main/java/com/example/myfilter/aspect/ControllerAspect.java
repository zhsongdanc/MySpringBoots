package com.example.myfilter.aspect;

import com.example.myfilter.bo.Student;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2022/12/7 16:02
 */
@Component
@Aspect
public class ControllerAspect {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);


    /**
     * 调用controller包下的任意类的任意方法时均会调用此方法
     */
    @Around("execution(* com.example.myfilter.controller.*.*(..))")
    public Object run1(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取方法参数值数组
        Object[] args = joinPoint.getArgs();
        //得到其方法签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //获取方法参数类型数组
        Class[] paramTypeArray = methodSignature.getParameterTypes();
        if (Student.class.isAssignableFrom(paramTypeArray[paramTypeArray.length - 1])) {
            //如果方法的参数列表最后一个参数是entityManager类型，则给其赋值
            Student student = new Student("demus","23");

            args[args.length - 1] = student;
        }
        logger.info("请求参数为{}",args);
        //动态修改其参数
        //注意，如果调用joinPoint.proceed()方法，则修改的参数值不会生效，必须调用joinPoint.proceed(Object[] args)
        Object result = joinPoint.proceed(args);
        logger.info("响应结果为{}",result);
        //如果这里不返回result，则目标对象实际返回值会被置为null
        return result;
    }

//    @Pointcut("execution(* com.company.controller.*.*(..))")
//    public void pointCut2() {}
//
//    @Around("pointCut2()")
//    public Object run2(ProceedingJoinPoint joinPoint) throws Throwable {
//        //获取方法参数值数组
//        Object[] args = joinPoint.getArgs();
//        //得到其方法签名
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        //获取方法参数类型数组
//        Class[] paramTypeArray = methodSignature.getParameterTypes();
//        if (EntityManager.class.isAssignableFrom(paramTypeArray[paramTypeArray.length - 1])) {
//            //如果方法的参数列表最后一个参数是entityManager类型，则给其赋值
//            args[args.length - 1] = entityManager;
//        }
//        logger.info("请求参数为{}",args);
//        //动态修改其参数
//        //注意，如果调用joinPoint.proceed()方法，则修改的参数值不会生效，必须调用joinPoint.proceed(Object[] args)
//        Object result = joinPoint.proceed(args);
//        logger.info("响应结果为{}",result);
//        //如果这里不返回result，则目标对象实际返回值会被置为null
//        return result;
//    }
}
