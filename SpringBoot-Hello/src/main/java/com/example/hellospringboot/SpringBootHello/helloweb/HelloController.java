package com.example.hellospringboot.SpringBootHello.helloweb;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/3/9 11:00
 */
@RestController
public class HelloController {

    @RequestMapping(path = "/hello")
    public String hello(){
        return "hello";
    }


}
