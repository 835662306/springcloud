package com.example.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/5 14:47
 * @Version 1.0
 */
@Component
public class MsgConsumer {
    @KafkaListener(topics = {"topic-1","topic-2"})
    public void processMessage(String content) {

        System.out.println("消息被消费"+content);
    }
}
