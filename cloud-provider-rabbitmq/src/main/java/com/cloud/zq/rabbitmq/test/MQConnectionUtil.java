package com.cloud.zq.rabbitmq.test;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MQConnectionUtil {
    public static Connection newConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("/zhuqing_test");
        factory.setUsername("zhuqing");
        factory.setPassword("123456");
        Connection newConnection = factory.newConnection();
        return newConnection;
    }
}
