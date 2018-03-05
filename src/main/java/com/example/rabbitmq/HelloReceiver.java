package com.example.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Consumer消费者
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/5 11:59
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "hello")//监听HelloSend类中hello
public class HelloReceiver {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver  : " + hello);
    }
}
