package com.demus.tcpdump.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/4/27 18:54
 */
@RestController
public class TestDumpController {

//    @Autowired
//    private RestTemplate restTemplate;


    @GetMapping("/hello")
    public String sayHello(){
        return "Hello, demus!";
    }

    @GetMapping("/timeout")
    public String timeout(){
        RestTemplate restTemplate = new RestTemplate();
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(3000);
        factory.setReadTimeout(5000);
        restTemplate.setRequestFactory(factory);


        UriComponents uri = UriComponentsBuilder.fromHttpUrl("http://10.43.22.54/timeout2")
                .port(9784)
                .build();

        ResponseEntity<String> response = restTemplate.getForEntity(uri.toUri(), String.class);

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "timeout";
    }
}
