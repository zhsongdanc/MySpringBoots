package com.example.myfilter.controller;

import com.example.myfilter.bo.Student;
import com.tencent.hr.common.util.encrypt.DigestUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2022/9/20 18:41
 */
@RestController
public class TestFilterController {

    @RequestMapping("/test/2")
    public String test(Student student) {
        return student.getName() + student.getAge();
    }

    @RequestMapping("/forward")
    public Student test2(HttpServletRequest request, HttpServletResponse response) {
        String timestamp = request.getHeader("timestamp");
        String signature = request.getHeader("signature");
        String token = "92fd161f35ece7ed535e93e7792d9d1384372c388b07b8a504aa";
        response.setHeader("name", "demussong");

        if(signature.equals(calculateSignature(timestamp, token))) {
            return new Student("szh","24");
        } else {
            return new Student("cd", "24");
        }

    }

    @RequestMapping("/test/3")
    public Student test3(HttpServletRequest request) {
//        throw new RuntimeException("errorrrrr");

        return new Student("szh","24");

    }

    public String calculateSignature(String timestamp, String token) {
        return DigestUtil.sha256().digest(timestamp + token + timestamp);
    }
}
