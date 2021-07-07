package com.cloud.zq.rabbitmq.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    private static final String QUEUE_NAME = "test_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接
        Connection newConnection = MQConnectionUtil.newConnection();
        //创建通道
        Channel channel = newConnection.createChannel();
        //创建队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        channel.basicQos(1);

        for (int i = 1; i <= 10; i++) {
            String msg = "测试生产者发出消息zhuqing_test--producer" + i;
            System.out.println("生产者发出消息" + msg);
            //发送消息
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        }

        channel.close();
        newConnection.close();

    }
}
