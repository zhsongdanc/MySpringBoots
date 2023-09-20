package com.example.springtest.pojo;

import com.example.springtest.config.MyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2022/12/29 20:06
 */
@Component
public class Fruit {
    private MyConfig myConfig;

    @Autowired
    public void setMyConfig(MyConfig myConfig) {
        this.myConfig = myConfig;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "myConfig=" + myConfig +
                '}';
    }
}
