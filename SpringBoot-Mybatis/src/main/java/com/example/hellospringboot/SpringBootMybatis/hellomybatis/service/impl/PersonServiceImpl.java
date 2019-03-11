package com.example.hellospringboot.SpringBootMybatis.hellomybatis.service.impl;

import com.example.hellospringboot.SpringBootMybatis.common.ResponseData;
import com.example.hellospringboot.SpringBootMybatis.hellomybatis.dto.Person;
import com.example.hellospringboot.SpringBootMybatis.hellomybatis.mapper.PersonMapper;
import com.example.hellospringboot.SpringBootMybatis.hellomybatis.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/3/9 12:02
 */
@Service
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private PersonMapper personMapper;
    @Override
    public ResponseData getPerson(int id) {
         Person person = personMapper.getPersonById(id);
         if(person == null){
             ResponseData responseData = new ResponseData();
             responseData.setRes(false);
             responseData.setMsg("没有找到对应信息");
             return responseData;
         }

         List list = new ArrayList();
         list.add(person);
         return new ResponseData(list);

    }
}
