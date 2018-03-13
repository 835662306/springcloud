package com.example.exception;

import com.example.publicResult.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常页面控制
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/13 16:38
 * @Version 1.0
 */
@RestControllerAdvice //控制器增强
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ActionResult handler(Exception e){
        if(e instanceof RequestLimitException){
            RequestLimitException exception = (RequestLimitException) e;
            logger.warn("ip:{}恶意访问", exception.getMessage());

            return new ActionResult(false, "恶意访问");
        }
        logger.error("[SysError]={}", e);
        return new ActionResult(false,"system error !!!");
    }
}
