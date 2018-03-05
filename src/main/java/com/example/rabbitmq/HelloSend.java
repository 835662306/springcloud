package com.example.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * product生产者
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/5 11:56
 * @Version 1.0
 */
@Component
public class HelloSend {

    @Autowired
    private AmqpTemplate rabbitTemplate;//rabbitTemplate是springboot 提供的默认实现

    public void send(){
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }
}
