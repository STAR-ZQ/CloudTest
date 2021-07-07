package com.cloud.zq.rabbitmq.test;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConsumerSMSFanout {
 private static final String QUEUE_NAME="consumerFanout_sms";
 private static final String EXCHANGE_NAME = "fanout_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("短信消费者获取信息===");
        Connection newConnection = MQConnectionUtil.newConnection();
        Channel channel = newConnection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"UTF-8");
                System.out.println("短信消费者获取生产者信息："+msg);
            }
        };
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
