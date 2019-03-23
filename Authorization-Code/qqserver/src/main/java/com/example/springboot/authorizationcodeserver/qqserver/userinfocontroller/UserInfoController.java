package com.example.springboot.authorizationcodeserver.qqserver.userinfocontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/3/23 16:36
 */
@RestController
public class UserInfoController {

    @RequestMapping("/qq/base_info")
    public String getBasicInfo(){
        return "base_info";
    }

    @RequestMapping("/qq/fans_list")
    public String getFansList(){
        return "fans_list";
    }

}
