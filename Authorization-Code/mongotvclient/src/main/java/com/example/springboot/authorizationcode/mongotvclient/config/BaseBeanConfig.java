package com.example.springboot.authorizationcode.mongotvclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/3/23 12:03
 */
@Configuration
public class BaseBeanConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
