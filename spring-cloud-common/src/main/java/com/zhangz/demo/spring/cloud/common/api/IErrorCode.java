package com.zhangz.demo.spring.cloud.common.api;

/**
 * API返回码接口
 * Created by zhangz
 */
public interface IErrorCode {
    /**
     * 返回码
     */
    long getCode();

    /**
     * 返回信息
     */
    String getMessage();
}
