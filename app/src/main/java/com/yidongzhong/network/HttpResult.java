package com.yidongzhong.network;

/**
 * Created by zex on 2017/8/13.
 */

public class HttpResult<T> {
    private int sta;      //1代表成功
    private String msg;

    private T data;

    public int getSta(){
        return sta;
    }

    public String getMsg(){
        return msg;
    }

    public T getData(){
        return data;
    }
}
