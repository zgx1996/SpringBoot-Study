package com.example.hellospringboot.springbootsecurity.hellosecurity.support;

import lombok.Data;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/3/9 16:56
 */
@Data
public class ResponseData {
    private int code;
    private boolean success;
    private String msg;

    public ResponseData(boolean success, String msg){
        this.success = success;
        this.msg = msg;
    }

}
