package com.example.springtest;

import com.example.springtest.config.MyConfig;
import com.example.springtest.pojo.DerivedTestBean;
import com.example.springtest.pojo.Fruit;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 通过@Configuration创建的bean -- beanName（类名第一个字母小写）
 * 通过@Bean创建的bean -- beanName（方法名）
 *
 */

public class SpringTestApplication {


        public static void main(String[] args) {
            startByJavaConfig();
        }

        static void startByJavaConfig() {
            // 通过配置类扫描
            AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MyConfig.class);
//            BeanDefinition beanDefinition = ac.getBeanDefinition("service");
//            BeanDefinition beanDefinition2 = ac.getBeanDefinition("myConfig");
//            String[] beanNamesForType = ac.getBeanNamesForType(MyConfig.class);
//            MyConfig myConfig = (MyConfig)ac.getBean("config");

            System.out.println(ac.getBean(Fruit.class));

            Fruit fruit = ac.getBean(Fruit.class);
            System.out.println(fruit);

        }

        static void startByXml() {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
            DerivedTestBean derivedTestBean = (DerivedTestBean) context.getBean("child");
            Fruit bean = context.getBean(Fruit.class);
            System.out.println(bean);
//            System.out.println("derivedTestBean的name = " + derivedTestBean.getName());
//            System.out.println("derivedTestBean的age = " + derivedTestBean.getAge());

        }


}
