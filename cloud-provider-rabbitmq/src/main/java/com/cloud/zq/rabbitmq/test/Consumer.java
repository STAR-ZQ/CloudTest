package com.cloud.zq.rabbitmq.test;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    private static final String QUEUE_NAME = "test_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("消费者01======");
        //获取连接
        Connection newConnection = MQConnectionUtil.newConnection();
        //获取通道
        Channel channel = newConnection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        channel.basicQos(1);

        //监听消息是否被消费
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                String msgString = new String(body, "UTF-8");
                System.out.println("消费者获取消息：" + msgString);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {

                } finally {
                    //手动应答模式
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };

        //监听队列   autiAck为false：手动应答   true:自动应答  消费完成后自动删除消费完成的信息
        channel.basicConsume(QUEUE_NAME, false, defaultConsumer);
    }
}
