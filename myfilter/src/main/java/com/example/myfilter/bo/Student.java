package com.example.myfilter.bo;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2022/10/11 16:14
 */
public class Student {
    private String name;
    private String age;

    public Student(){

    }

    public Student(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
