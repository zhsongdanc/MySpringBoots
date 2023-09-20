package com.example.simple;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2022/11/15 15:56
 */
public class SortTest {
    public static void main(String [] args){
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("red", "szh", 9));
        apples.add(new Apple("red", "abc", 5));
        apples.add(new Apple("ye", "abc", 13));
        apples.add(new Apple(null, "cd", 6));




//		Comparator<Apple> byName = Comparator.comparing(Apple::getName);
//		Comparator<Apple> bySizeDesc = Comparator.comparing(Apple::getSize).reversed();
//		// 先以名称升序排列，再按照size倒叙排列
//		apples.sort(byName.thenComparing(bySizeDesc));
        //用java8 的lambda 表达式
//		apples.sort(Comparator.comparing(Apple::getSize).thenComparing(Apple::getName,Comparator.reverseOrder()));
        apples.sort(Comparator.comparing(Apple::getSize).thenComparing(Apple::getName));
//		apples.sort(Comparator.comparing(Apple::getSize).thenComparing(Apple::getName));
        apples.forEach(System.out::println);
    }
}

class Apple{
    public Apple(String color, String name, Integer size) {
        this.color = color;
        this.name = name;
        this.size = size;
    }

    private String color;
    private String name;
    private Integer size;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "name='" + name + '\'' +
                ", size=" + size +
                '}';
    }
}