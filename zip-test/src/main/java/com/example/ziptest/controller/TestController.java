package com.example.ziptest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2022/10/19 09:32
 */
@RequestMapping("hello")
@RestController
public class TestController {
	@RequestMapping("12")
	public String test() {
		return "123";
	}

	@RequestMapping("/123")
	public String test2() {
		return "1234";
	}
}
