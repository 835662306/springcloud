package com.example.cloud;

import com.example.rabbitmq.HelloSend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试RabbitMQ,在控制台可以看到消息
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/5 13:14
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMQTest {
    @Autowired
    private HelloSend helloSend;

    @Test
    public void hello() throws Exception {
        helloSend.send();
    }
}
