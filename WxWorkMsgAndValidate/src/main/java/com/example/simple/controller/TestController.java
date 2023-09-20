package com.example.simple.controller;

import com.example.simple.help.WXBizMsgCrypt;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2022/11/3 13:18
 */
@RestController
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Value("${hr.name}")
    private String str;


    @Value("${name}")
    private String str2;

    @Value("#{${proxy.whitelist}}")
    List<String> appWhiteList;

    @GetMapping("/nb")
    public String testHello(String val) {
//        redisTemplate.opsForValue().set("key1", "value1");
        System.out.println(str);
        System.out.println(str2);
        return val;
    }

    @GetMapping("/redirect")
    public void redirectTest(HttpServletResponse response, String name) {
        response.addHeader("Location", "http://localhost:9768/to");
        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
    }

    @GetMapping("/to")
    public void toRedirect(HttpServletRequest request) {
        String name = request.getParameter("name");
        System.out.println(name);
    }


    @GetMapping("/")
    public String workWx(HttpServletRequest request, @RequestParam String echostr,
            @RequestParam String msg_signature,
            @RequestParam String timestamp,
            @RequestParam String nonce) throws Exception{


        String sToken = "R6wLQ3WSpBWjprJO0P";
        String sEncodingAESKey = "B7GwnVUGxw67m3EBqkPe22DClt4wCCFMPEnGFsB8TD8";
        String sCorpID = "ww30a7a005150b6fb9";
        log.info("echostr={}", echostr);
        log.info("msg_signature={}", msg_signature);
        log.info("timestamp={}", timestamp);
        log.info("nonce={}", nonce);

        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);

        String sEchoStr = ""; //需要返回的明文
        try {
            sEchoStr = wxcpt.VerifyURL(msg_signature, timestamp,
                    nonce, echostr);
            log.info("verifyurl echostr:{} " , sEchoStr);
//            System.out.println("verifyurl echostr: " + sEchoStr);
            // 验证URL成功，将sEchoStr返回
            // HttpUtils.SetResponse(sEchoStr);
        } catch (Exception e) {
            //验证URL失败，错误原因请查看异常
            e.printStackTrace();
        }
        return sEchoStr;

    }


//    public static String sort(String[] myArray) {
//
//        int size = myArray.length;
//
//        Arrays.sort(myArray, (o1,o2) -> (o1 + o2).compareTo((o2 + o1)));
//        String result = myArray[0];
//        for (int i = 1; i < myArray.length; i++) {
//            result = result + myArray[i];
//        }
//
//        return result;
//    }
//
//    public static String decode(String key, String content) {
//        byte[] KEY_VI = key.substring(key.length()/2).getBytes(StandardCharsets.UTF_8);
//        try {
//             javax.crypto.SecretKey secretKey = new javax.crypto.spec.SecretKeySpec(key.getBytes(), "AES");
//             javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("AES/CBC/PKCS5Padding");
//             cipher.init(javax.crypto.Cipher.DECRYPT_MODE, secretKey, new javax.crypto.spec.IvParameterSpec(KEY_VI));
//
//             // 将加密并编码后的内容解码成字节数组
//            byte[] byteContent = Base64.getDecoder().decode(content);
//             // 解密
//             byte[] byteDecode = cipher.doFinal(byteContent);
//             return new String(byteDecode, java.nio.charset.StandardCharsets.UTF_8);
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     return null;
//    }
//
//    public static String encode(String key, String plaintext) {
//        byte[] KEY_VI = key.substring(key.length()/2).getBytes(StandardCharsets.UTF_8);
//        try {
//            javax.crypto.SecretKey secretKey = new javax.crypto.spec.SecretKeySpec(key.getBytes(), "AES");
//            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("AES/CBC/PKCS5Padding");
//            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new javax.crypto.spec.IvParameterSpec(KEY_VI));
//
//
//            // 加密
//            byte[] byteEncode = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
//
//            // 编码
//            byte[] encode = Base64.getEncoder().encode(byteEncode);
//            return new String(encode, java.nio.charset.StandardCharsets.UTF_8);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }



//
//
//    public static void main(String[] args) {
//        String encodeKey = "YWFhYWJiYmJkZGRkY2NjY2FhYWFiYmJiZGRkZGNjY2M=";
//        String key = new String(Base64.getDecoder().decode(encodeKey));
//        String demus = encode(key, "我是demus");
//        String decode = decode(key, demus);
//        System.out.println(decode);
//    }






}
