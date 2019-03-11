package com.example.hellospringboot.SpringBootMybatis.hellomybatis.controller;

import com.example.hellospringboot.SpringBootMybatis.common.ResponseData;
import com.example.hellospringboot.SpringBootMybatis.hellomybatis.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/3/9 12:13
 */
@RestController(value = "/person")
public class PersonController {

    @Autowired
    private IPersonService personService;

    @RequestMapping("/person/{id}")
    public ResponseData getPerson(@PathVariable(value = "id") int id){
        return personService.getPerson(id);
    }

}
