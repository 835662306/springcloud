package com.example.common;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

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
public class DoLoginAspect {
    private Logger logger = Logger.getLogger(DoLoginAspect.class);

    //所有controller方法&&指向DoLogin这个AOP
    @Pointcut("execution(public * com.example.controller..*(..)) && @annotation(com.example.common.aspect.DoLogin)")
    public void log(){}

    @Before("log()")//在方法执行之前执行
//    @Around("log()")//在执行方法前后执行
    public void doLogin(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        System.out.println(request.getSession().getId());
    }
}
