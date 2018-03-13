package com.example.common;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * AOP
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/13 14:37
 * @Version 1.0
 */
@Aspect
@Component
public class AspectStyle {
    private Logger logger = Logger.getLogger(AspectStyle.class);

    //所有controller方法
    @Pointcut("execution(public * com.example.controller..*(..))")
    public void log(){}

}
