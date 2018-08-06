package com.bc.response;

/**
 * Created by beijixiong on 2017/5/27.
 */
public class Response {

    protected int code;
    protected String message;

    public Response(){}

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
