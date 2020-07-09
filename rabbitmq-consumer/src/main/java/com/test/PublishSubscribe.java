package com.test;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zzg
 * @Description
 *
 * channel.basicQos(1);
 * 使用basicQos方法时，参数为prefetchCount 值，告诉RabbitMQ不要在同一时间给一个消费者超过prefetchCount条消息。
 * 换句话说，只有在消费者空闲的时候会发送下一条信息。
 */
public class PublishSubscribe {

    private static final String EXCHANCE_FANOUT_INFORM = "exchange_fanout_inform";
    private static final String QUEUE_INFORM_EMAIL = "queue_inform-email";
    private static final String QUEUE_INFORM_SMS = "queue-inform-sms";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            /**
             * 声明队列
             */
            channel.queueDeclare(QUEUE_INFORM_SMS, true, false, false, null);

            /**
             * 声明交换机
             */
            channel.exchangeDeclare(EXCHANCE_FANOUT_INFORM, BuiltinExchangeType.FANOUT);

            /**
             *  绑定交换机
             */
            channel.queueBind(QUEUE_INFORM_SMS, EXCHANCE_FANOUT_INFORM, "");

            DeliverCallback deliverCallback = (consumerTag, deliver) -> {
                String message = new String(deliver.getBody(), "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            };

            channel.basicConsume(QUEUE_INFORM_SMS, true, deliverCallback, (consumerTag) -> {});

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}