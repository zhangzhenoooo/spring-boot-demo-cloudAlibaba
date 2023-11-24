package com.zhangz.spring.cloud.common.exception;

/**
 * zhangz
 */
public class BussinessException extends Exception{
    private String msg; //提示信息

    public BussinessException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BussinessException(String message, String msg) {
        super(message);
        this.msg = msg;
    }

    public BussinessException(String message, Throwable cause, String msg) {
        super(message, cause);
        this.msg = msg;
    }

    public BussinessException(Throwable cause, String msg) {
        super(cause);
        this.msg = msg;
    }

    public BussinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String msg) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
