package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * Kafka 是一种高吞吐的分布式发布订阅消息系统，能够替代传统的消息队列用于解耦合数据处理，缓存未处理消息等，
 * 同时具有更高的吞吐率，支持分区、多副本、冗余，因此被广泛用于大规模消息数据处理应用
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/5 14:39
 * @Version 1.0
 */
@Configuration
@EnableKafka
public class KafkaConfig {

}
