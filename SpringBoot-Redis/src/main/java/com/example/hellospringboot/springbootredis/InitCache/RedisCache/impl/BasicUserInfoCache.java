package com.example.hellospringboot.springbootredis.InitCache.RedisCache.impl;

import com.example.hellospringboot.springbootredis.InitCache.RedisCache.RedisCacheInit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/3/23 20:34
 */
@Component
public class BasicUserInfoCache implements RedisCacheInit {


    private static final String Basic_User_Cache_key_Perfix = "userinfo:";

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void init() {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setStringSerializer(stringRedisSerializer);
        HashMap hashMap = new HashMap();
        User user1 = new User(1, "AA", 10);
        User user2 = new User(2, "BB", 10);
        User user3 = new User(3, "CC", 10);
        hashMap.put(user1.getUserId(),user1);
        hashMap.put(user2.getUserId(),user2);
        hashMap.put(user3.getUserId(),user3);


        hashMap.forEach((key, value) -> {
            String s = "";
            try {
                s = objectMapper.writeValueAsString(value);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
            connection.lPush(stringRedisSerializer.serialize(Basic_User_Cache_key_Perfix + (String.valueOf(key))),stringRedisSerializer.serialize(s));
        });
    }

    @Data
    @AllArgsConstructor
    protected class User implements Serializable {
        private int userId;
        private String userName;
        private int age;


    }

}
