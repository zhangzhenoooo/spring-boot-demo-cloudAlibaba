package com.zhangz.spring.cloud.common.exception;

/**
 * zhangz
 */
public class LoginException extends Exception{
    private String msg; //提示信息
    
    public LoginException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public LoginException(String message, String msg) {
        super(message);
        this.msg = msg;
    }

    public LoginException(String message, Throwable cause, String msg) {
        super(message, cause);
        this.msg = msg;
    }

    public LoginException(Throwable cause, String msg) {
        super(cause);
        this.msg = msg;
    }

    public LoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String msg) {
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
