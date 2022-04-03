package com.youxin.servicebase.exception;

import com.youxin.commonutils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.error();
    }

    @ExceptionHandler(YouXinException.class)
    @ResponseBody
    public Result error1(YouXinException e) {
        e.printStackTrace();
        return Result.error().message("状态码:"+e.getCode()+" 异常信息："+e.getMsg());
    }
}
