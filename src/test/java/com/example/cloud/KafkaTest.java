package com.example.cloud;

import com.example.kafka.MsgProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试kafka
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/5 14:49
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaTest {
    @Autowired
    private MsgProducer msgProducer;

    @Test
    public void test() throws Exception {

        msgProducer.sendMsg("topic-1", "topic--------1");
        msgProducer.sendMsg("topic-2", "topic--------2");
    }
}
