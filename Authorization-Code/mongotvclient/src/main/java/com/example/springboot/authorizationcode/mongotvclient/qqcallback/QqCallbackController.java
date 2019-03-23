package com.example.springboot.authorizationcode.mongotvclient.qqcallback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/3/23 10:29
 */
@RestController
public class QqCallbackController {


    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/mongotv/qq/redirect")
    public Object qqCallBack(@RequestParam(value = "code")String code){
        //code 获取 token

        MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(postParameters, headers);

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity("http://localhost:9090/oauth/token?client_id=mongotv&client_secret=mongotv&grant_type=authorization_code&code="+code+"&redirect_uri=http://localhost:8081/mongotv/qq/redirect", r, String.class);
        String body = stringResponseEntity.getBody();

        return body;
    }

}
