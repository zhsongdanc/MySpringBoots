package com.example.simple.controller;

import com.example.simple.common.dto.UserDTO;
import com.example.simple.common.vo.UserVO;
import javax.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @Author: demussong
 * @Description:
 * 使用@Valid 或 @Validated都可以校验
 * @Date: 2022/11/18 17:19
 */
@RestController
public class ValidateController {
    @GetMapping("/validate")
    public String test(@Valid UserDTO userDTO){
        System.out.println(userDTO.getUserName());
        System.out.println(userDTO.getAge());

        return userDTO.getUserName() + userDTO.getAge();
    }

    @GetMapping("/va2")
    public String test2(String userName){


        return userName;
    }
}
