package com.demus.mysecurity;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2022/10/21 15:39
 */
public class EmptyTest {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("");
		list.add("32");
		System.out.println(StringUtils.isEmpty(list));
		System.out.println(list.size());

		List<String> list2 = list.stream().filter(e -> !StringUtils.isEmpty(e)).collect(Collectors.toList());

		System.out.println(StringUtils.isEmpty(list2));
		System.out.println(list2.size());
	}
}
