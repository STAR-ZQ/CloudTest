package com.cloud.zq.rabbitmq.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class FanoutProducer {
    private static final String EXCHANGE_NAME = "fanout_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection newConnection = MQConnectionUtil.newConnection();
        Channel channel = newConnection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String msg = "fanout_exchange_msg";
        channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes());
        System.out.println("生产者发送消息:" + msg);
//        channel.close();
//        newConnection.close();
    }
}
