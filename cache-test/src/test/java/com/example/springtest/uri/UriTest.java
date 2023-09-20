package com.example.springtest.uri;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/1/13 10:09
 */

public class UriTest {

    @Test
    public void testUriComponent() {
        String name = "szh";
        Map<String, String> map = new HashMap<>();
        map.put("name", "demus");
        // 对name进行替换
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http").host("www.baeldung.com").path("/{name}")
                .buildAndExpand(map);
        System.out.println(uriComponents.toString());

        UriComponents uriComponents2 = UriComponentsBuilder.newInstance()
                .scheme("http").host("www.google.com")
                .path("/").query("q={keyword}").buildAndExpand("baeldung");



        System.out.println(uriComponents.encode());

    }

    @Test
    public void test2() {
        String redirectPathTemplate = "/docqq/auth/{operate}/{operateId}";
        String redirectUrl = "https://proxy.files.woa.com/fileservices/redirect-demo/hr-fileservices-thirdpart" + redirectPathTemplate;

        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("operateId", "operateId12345");
        pathVariables.put("operate", "operateIdname");
        // 在使用UriComponentsBuilder时候一定要注意编码问题，不要重复编码
        String url = UriComponentsBuilder.fromHttpUrl(redirectUrl).buildAndExpand(pathVariables).toString();
        System.out.println(url);
    }

    @Test
    public void testReplace() throws Exception{
        // 有则删除，无则不变
        // http://www.baidu.com?age=18
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http").host("www.baidu.com").query("name=szh").query("age=18")
                .replaceQueryParam("name")
                .build();

        UriComponents uriComponents2 = UriComponentsBuilder.newInstance()
                .scheme("http").host("www.baidu.com").query("name=szh").query("age=18")
                .replaceQueryParam("test")
                .build();

        System.out.println(uriComponents2.toString());


        URI uri = new URI("www.baidu.com?Name=szh&age=67");
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(uri).scheme("http");
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("Name", Arrays.asList("szh"));
        params.put("age", Arrays.asList("67"));
        params.forEach((key, value) -> {
            if("name".equalsIgnoreCase(key)) {
                uriBuilder.replaceQueryParam(key);
            }
        });
        uri=uriBuilder.build(true).toUri();
        System.out.println(uri.toString());
    }

}
