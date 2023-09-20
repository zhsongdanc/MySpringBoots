package com.demus.mysecurity.controller;

import com.tencent.hr.base.dto.TransDTO;
import com.tencent.hr.message.common.Constants;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2022/10/20 20:55
 */

@RestController
public class DemoController {

	@Secured({Constants.ROLE_ADMIN, Constants.ROLE_USER})
	@RequestMapping("/test1")
	public TransDTO<String> test() {
		TransDTO<String> transDTO = new TransDTO<>();
		transDTO.setData("test1");
		return transDTO;
	}

	@Secured({Constants.ROLE_APP_READER})
	@RequestMapping("/test2")
	public TransDTO<String> test2() {
		TransDTO<String> transDTO = new TransDTO<>();
		transDTO.setData("test2");
		return transDTO;
	}

	@RequestMapping("/test3")
	public TransDTO<String> test3() {
		TransDTO<String> transDTO = new TransDTO<>();
		transDTO.setData("test2");
		return transDTO;
	}


}
