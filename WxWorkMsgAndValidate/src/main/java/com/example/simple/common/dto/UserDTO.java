package com.example.simple.common.dto;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2022/11/18 17:21
 */

import javax.validation.constraints.NotNull;

public class UserDTO {

    private String userName;
    @NotNull
    private String age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
