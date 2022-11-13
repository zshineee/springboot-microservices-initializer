package com.github.zshine.common.exception;

import com.github.zshine.common.response.BaseJsonRsp;
import com.github.zshine.common.response.FormJsonRsp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@ControllerAdvice
@Configuration
@Slf4j
public class GlobalExceptionHandler {


    /**
     * 接口参数校验异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BaseJsonRsp constraintViolationExceptionHandle(ConstraintViolationException e) {
        String errMsg = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(","));
        return FormJsonRsp.fail(errMsg);
    }

    /**
     * 接口参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BaseJsonRsp methodArgumentNotValidExceptionHandle(MethodArgumentNotValidException e) {
        String errMsg = e.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));
        return FormJsonRsp.fail(errMsg);
    }

    /**
     * 接口参数校验异常
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BaseJsonRsp missingServletRequestParameterExceptionHandle(MissingServletRequestParameterException e) {
        return FormJsonRsp.fail(e.getParameterName() + "不能为空");
    }

    /**
     * 自定义异常
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BaseJsonRsp customExceptionHandle(Exception e) {
        return FormJsonRsp.fail(e.getMessage());
    }

    /**
     * 其他异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BaseJsonRsp exceptionHandle(Exception e) {
        e.printStackTrace();
        return FormJsonRsp.fail("系统异常，请联系管理员");
    }
}


