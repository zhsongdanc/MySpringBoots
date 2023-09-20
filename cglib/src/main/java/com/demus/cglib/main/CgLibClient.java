package com.demus.cglib.main;

import com.demus.cglib.service.HelloImpl;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;


/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/1/10 16:39
 */
public class CgLibClient {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloImpl.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("before method run...");
                Object result = methodProxy.invokeSuper(o, args);
                System.out.println("after method run...");
                return result;
            }
        });

        HelloImpl hello = (HelloImpl) enhancer.create();
//        hello.sayHello();
    }


}
