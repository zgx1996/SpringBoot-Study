package com.example.hellospringboot.SpringBootMybatis.hellomybatis.mapper;

import com.example.hellospringboot.SpringBootMybatis.hellomybatis.dto.Person;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/3/9 12:04
 */
@Mapper
public interface PersonMapper {
    Person getPersonById(int id);
}
