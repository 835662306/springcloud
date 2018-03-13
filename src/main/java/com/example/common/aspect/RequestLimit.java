package com.example.common.aspect;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * 控制器访问次数限制
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/13 14:54
 * @Version 1.0
 */
@Target({ElementType.METHOD})//注解的作用目标 方法
@Retention(RetentionPolicy.RUNTIME)// 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Order(Ordered.HIGHEST_PRECEDENCE)//最高优先级
@Documented//说明该注解将被包含在javadoc中
public @interface RequestLimit {
    /**
     * 允许访问的次数，默认值MAX_VALUE
     */
    int count() default Integer.MAX_VALUE;

    /**
     * 时间段，单位为毫秒，默认值一分钟
     */
    long time() default 60000;
}
