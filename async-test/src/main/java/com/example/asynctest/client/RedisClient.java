package com.example.asynctest.client;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/4/27 09:52
 */
@Slf4j
@Component
public class RedisClient {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RestTemplate restTemplate;

    public void set() {
        String key = "firstcallDos";
        String response = callDos();

        long startTime = System.currentTimeMillis();
        stringRedisTemplate.opsForValue().set(key, response);

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        log.info("consume time:{}ms", elapsedTime);
    }

    public String callDos() {
        String url = "http://localhost";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .port(3369)
                .path("/open-api/config/hrmd/md-api-public-core-staff-info-realtime/hrmd-test/data");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("hrgw-appname", "hrmd-test");

        Map<String, Object> requestBody = new HashMap<>();
        Map<String ,Object> queryCondition = new HashMap<>();
        Map<String, Object> argMap = new HashMap<>();

        queryCondition.put("argMap", argMap);
        queryCondition.put("prevId", null);

        requestBody.put("queryCondition", queryCondition);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = this.restTemplate.postForEntity(builder.build().toUri(), requestEntity,
                String.class);


        ObjectMapper objectMapper = new ObjectMapper();
        String result = null;
        try{
            result = objectMapper.writeValueAsString(response);

        }catch (JsonProcessingException e){
            log.error("序列化出现错误");
        }
        return result;
    }

    private RestTemplate getRestTemplate() {

        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        // 设置连接请求不够用等待时间，建立连接超时时间，连接建立后等待数据返回超时时间
        httpRequestFactory.setConnectionRequestTimeout(3000);
        httpRequestFactory.setConnectTimeout(3000);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(httpRequestFactory);


        return restTemplate;
    }

    public static void main(String[] args) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        ObjectMapper objectMapper2 = new ObjectMapper();
        objectMapper2.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        ObjectMapperTest test = new ObjectMapperTest();
        test.setAge("26");
        test.setName("demus");
        test.setCount(23);
        String json = objectMapper.writeValueAsString(test);
        ObjectMapperTest test2 = objectMapper2.readValue(json, ObjectMapperTest.class);
        System.out.println(test2.getAge());
        System.out.println(test2.getName());
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
    static class ObjectMapperTest{
        private String name;
        public String age;

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        private Integer count;

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
}
