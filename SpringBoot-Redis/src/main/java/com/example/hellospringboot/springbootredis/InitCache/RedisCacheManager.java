package com.example.hellospringboot.springbootredis.InitCache;

import com.example.hellospringboot.springbootredis.InitCache.RedisCache.RedisCacheInit;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/3/23 20:28
 */
@Component
public class RedisCacheManager implements ApplicationRunner , ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        /*获取所有实现了RedisInit接口的Bean*/
        Map<String, RedisCacheInit> redisCacheInitBeans = applicationContext.getBeansOfType(RedisCacheInit.class);
        if(redisCacheInitBeans != null && redisCacheInitBeans.size() > 0){
            redisCacheInitBeans.values().stream().forEach(item -> item.init());
        }

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
