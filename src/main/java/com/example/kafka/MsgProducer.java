package com.example.kafka;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/5 14:40
 * @Version 1.0
 */
@Component
public class MsgProducer {

    private Logger log = Logger.getLogger(MsgProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMsg(String topicName, String jsonData){
        log.info("向kafka推送数据:[{}]"+jsonData);
        try {
            kafkaTemplate.send(topicName,jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("发送数据出错！！！{}{}"+topicName+"；数据："+jsonData);
            log.error("发送数据出错=====>", e);
        }

        kafkaTemplate.setProducerListener(new ProducerListener<String, String>() {
            @Override
            public void onSuccess(String s, Integer integer, String s2, String s3, RecordMetadata recordMetadata) {

            }

            @Override
            public void onError(String s, Integer integer, String s2, String s3, Exception e) {

            }

            @Override
            public boolean isInterestedInSuccess() {
                log.info("数据发送完毕");
                return false;
            }
        });
    }
}
