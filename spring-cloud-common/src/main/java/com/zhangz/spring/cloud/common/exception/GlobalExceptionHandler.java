package com.zhangz.spring.cloud.common.exception;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.zhangz.spring.cloud.common.api.CommonResult;
import com.zhangz.spring.cloud.common.api.ResultCode;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLSyntaxErrorException;

/**
 * 全局异常处理类
 * Created by macro on 2020/2/27.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = BussinessException.class)
    public CommonResult handle(BussinessException e) {
        return CommonResult.failed(ResultCode.FAILED,e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = SystemException.class)
    public CommonResult handle(SystemException e) {
        return CommonResult.failed(ResultCode.FAILED,e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public CommonResult handleValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField()+fieldError.getDefaultMessage();
            }
        }
        return CommonResult.validateFailed(message);
    }

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public CommonResult handleValidException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField()+fieldError.getDefaultMessage();
            }
        }
        return CommonResult.validateFailed(message);
    }

    @ResponseBody
    @ExceptionHandler(value = SQLSyntaxErrorException.class)
    public CommonResult handleSQLSyntaxErrorException(SQLSyntaxErrorException e) {
        String message = e.getMessage();
        if (StringUtils.isNotEmpty(message) && message.contains("denied")) {
            message = "暂无权限！请联系管理员";
        }
        return CommonResult.failed(message);
    }
}
