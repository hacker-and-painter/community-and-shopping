package com.beautifulsoup.chengfeng.exception;

public class ParamException extends RuntimeException {
    private String msg;

    public ParamException(String message) {
        this.msg=message;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
