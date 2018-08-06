package com.bc.response;

/**
 * Created by beijixiong on 2017/5/27.
 */
public class ResponseWithData<T> extends Response {

    protected T data;

    public ResponseWithData(){

    }

    public ResponseWithData(int code, String message) {
        super(code, message);
    }

    public ResponseWithData(int code, String message, T data) {
        super(code, message);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
