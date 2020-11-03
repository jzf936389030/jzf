package com.sifei.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice("com.sifei")
public class SysExceptionResolver {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Object exceptionHandler(BusinessException e){
        log.error("",e);
        return JsonResponse.serverError().setMsg(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object exceptionHandler(Exception e){
        log.error("",e);
        return JsonResponse.serverError().setMsg("访问异常");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public Object exceptionHandler(MissingServletRequestParameterException e){
        log.error("",e);
        return JsonResponse.serverError().setMsg("数据输入异常");
    }


}
