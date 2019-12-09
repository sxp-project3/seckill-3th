package com.suixingpay.response;


import com.suixingpay.enumeration.CodeEnum;

/**
 * @author kongjian
 */
public class Response<T> {
    private String code;
    private String msg;
    private T data;

    public Response() {

    }

    public Response(String code, String msg) {
        this.code = code;
        this.msg = msg;

    }

    public Response(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Response getInstance(CodeEnum codeEnum) {
        return new Response(codeEnum.getCode(), codeEnum.getMsg());
    }

    public static <T> Response getInstance(CodeEnum codeEnum, T data) {
        return new Response(codeEnum.getCode(), codeEnum.getMsg(), data);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
