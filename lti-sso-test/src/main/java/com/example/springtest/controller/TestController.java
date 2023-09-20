package com.example.springtest.controller;



import com.example.springtest.service.CacheService;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2022/10/31 17:00
 */
@RestController
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @Resource
    private CacheService cacheService;

    @GetMapping("/test")
    public String test() {
        return cacheService.test();
    }

    @GetMapping("/sso")
    public String sso() {

        System.out.println("");
        return "ok";
    }

    @PostMapping("/upload")
    public void uploadExcel( MultipartFile file123){
        System.out.println(file123.getName());;
        System.out.println("");

    }
}
