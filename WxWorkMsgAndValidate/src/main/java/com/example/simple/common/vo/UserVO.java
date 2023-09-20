package com.example.simple.common.vo;

import javax.validation.constraints.NotNull;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2022/11/18 18:04
 */
public class UserVO {
    @NotNull
    private String userName;
    private String age;

    @Override
    public String toString() {
        return "UserVO{" +
                "userName='" + userName + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
