package com.example.springtest.controller;

import org.apache.catalina.User;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/27 12:47
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/user")
    public ResponseEntity<User> getUser(Authentication authentication) {
//        User user = new User();
//        user.setUsername(authentication.getName());
//        user.setAuthorities(authentication.getAuthorities());
//        return ResponseEntity.ok(user);
        return null;
    }
}
