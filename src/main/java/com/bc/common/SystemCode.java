package com.bc.common;

/**
 * Created by beijixiong on 2017/5/27.
 */
public enum SystemCode {

    SUCCESS( 0, "success"),
    LINK_ERROR( 1000, "网络异常"),


    CUSTOM_ERROR(9999,""),/**自定义描述错误**/

    ;


    private int code;
    private String message;

    SystemCode(int code, String message){
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
