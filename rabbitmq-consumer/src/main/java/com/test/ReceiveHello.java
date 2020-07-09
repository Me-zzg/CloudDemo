package com.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zzg
 * @Description
 */
public class ReceiveHello {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            /**
             * 声明队列
             * queue    队列名称
             * durable  是否持久化
             * exclusive    是否独占 1.只对首次声明它的连接（Connection）可见 2.会在其连接断开的时候自动删除。
             */
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            DeliverCallback deliverCallback = (consumerTag, delivery) ->{
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            };

            /**
             * queue    队列名称
             * autoAck  是否自动回复，设置为true为表示消息接收到自动向mq回复接收到了，mq接收到回复会删除消息，设置为false则需要手动回复
             * deliverCallback  消费消息的方法，消费者接收到消息后调用此方法
             * cancelCallback   取消使用者时的回调
             */
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {});
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}