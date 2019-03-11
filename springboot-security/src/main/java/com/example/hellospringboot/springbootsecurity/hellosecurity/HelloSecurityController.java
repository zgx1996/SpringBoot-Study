package com.example.hellospringboot.springbootsecurity.hellosecurity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/3/9 16:16
 */
@RestController
public class HelloSecurityController {

    @RequestMapping(value = "/helloSecurity")
    public String helloSecurity(){
        return "hello security";
    }

}
