package com.zhangz.demo.spring.cloud.common.exception;

/**
 * zhangz
 */
public class SystemException extends Exception{
    private String msg; //提示信息

    public SystemException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public SystemException(String message, String msg) {
        super(message);
        this.msg = msg;
    }

    public SystemException(String message, Throwable cause, String msg) {
        super(message, cause);
        this.msg = msg;
    }

    public SystemException(Throwable cause, String msg) {
        super(cause);
        this.msg = msg;
    }

    public SystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String msg) {
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
