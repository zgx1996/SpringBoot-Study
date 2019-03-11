package com.example.hellospringboot.SpringBootMybatis.common;

import lombok.Data;

import java.util.List;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/3/9 12:11
 */
@Data
public class ResponseData {

    private String msg;

    private int code;

    private boolean res;

    private List list;


    public ResponseData(){
        this.res = true;
    }

    public ResponseData(List list){
        this.res = true;
        this.list = list;
    }

}
