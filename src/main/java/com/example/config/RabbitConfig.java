package com.example.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/5 11:54
 * @Version 1.0
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue queue(){
        return  new Queue("hello");
    }
}
