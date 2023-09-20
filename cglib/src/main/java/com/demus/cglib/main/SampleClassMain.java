package com.demus.cglib.main;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/1/10 17:25
 */
public class SampleClassMain {
    public void testFixedValue(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "Hello cglib";
            }
        });
        SampleClass proxy = (SampleClass) enhancer.create();
        System.out.println(proxy.test(null)); //拦截test，输出Hello cglib
        System.out.println("=========");
        System.out.println(proxy.toString());
        System.out.println("=========");
        System.out.println(proxy.getClass());
        System.out.println("=========");
        System.out.println(proxy.hashCode());
    }

    public static void main(String[] args) {
        new SampleClassMain().testFixedValue();
    }


}
