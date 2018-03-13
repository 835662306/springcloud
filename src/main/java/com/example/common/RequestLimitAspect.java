package com.example.common;

import com.example.common.aspect.RequestLimit;
import com.example.exception.RequestLimitException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/13 14:49
 * @Version 1.0
 */
@Component
@Aspect
public class RequestLimitAspect {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Before("execution(public * com.example.controller..*(..)) && @annotation(params)")
    public void requestLimit(JoinPoint joinPoint, RequestLimit params){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //获取ip
        String LocalAddr = request.getLocalAddr();
        //获取访问次数
        int num = params.count();

        String redisKey = "HTTP-Intercept:"+ LocalAddr;

        //查看缓存中是否存在redisKey
        if(stringRedisTemplate.hasKey(redisKey)){
            //如果存在，次数加1
            stringRedisTemplate.boundValueOps(redisKey).increment(1);

            if(num < Integer.valueOf(stringRedisTemplate.opsForValue().get(redisKey))){
                //重新刷新时间
                stringRedisTemplate.expire(redisKey, params.time(), TimeUnit.SECONDS);
                //如果拦截次数 超过限制, 将加入黑名单 不让访问了

                throw new RequestLimitException(LocalAddr);
            }
        }else {
            stringRedisTemplate.opsForValue().set(redisKey, "0", params.time(), TimeUnit.SECONDS);
        }
    }
}
