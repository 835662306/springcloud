package com.example.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 先运行 Consumer ，这样当生产者发送消息的时候能在消费者后端看到消息记录
 * 接着运行 Producer ,发布一条消息，在 Consumer 的控制台能看到接收的消息
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/5 10:59
 * @Version 1.0
 */
public class Product {

    public static void main(String [] args) throws IOException, TimeoutException{
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");
        //建立到代理服务器到连接
        Connection connection = factory.newConnection();
        //获取通道
        final Channel channel = connection.createChannel();
        //声明交换器
        String exchangeName = "hello-exchange";
        channel.exchangeDeclare(exchangeName, "direct", true);

        String routingKey = "hola";
        //发布消息
        byte[] messageBodyBytes = "this is RabbitMQ".getBytes();
        channel.basicPublish(exchangeName, routingKey, null, messageBodyBytes);

        channel.close();
        connection.close();

    }
}
