package com.test;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zzg
 * @Description 路由模式 Routing（https://www.rabbitmq.com/tutorials/tutorial-four-java.html）
 *  在发布订阅模式之上 增加 routingKey 指定
 *      路由模式：
 *          1、每个消费者监听自己的队列，并且设置routingkey。
 *          2、生产者将消息发给交换机，由交换机根据routingkey来转发消息到指定的队列。
 *
 */
public class Routing {

    private static final String EXCHANCE_FANOUT_INFORM = "exchange_fanout_inform";
    private static final String QUEUE_INFORM_EMAIL = "queue_inform-email";
    private static final String QUEUE_INFORM_SMS = "queue-inform-sms";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = null;
        Channel channel= null;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();

            channel.queueDeclare(QUEUE_INFORM_EMAIL, true, false, false, null);
            channel.queueDeclare(QUEUE_INFORM_SMS, true, false, false, null);
            /**
             *  声明交换机
             *  exchange    交换机名称
             *  type    类型  DIRECT("direct"), FANOUT("fanout"), TOPIC("topic"), HEADERS("headers");
             */
            channel.exchangeDeclare(EXCHANCE_FANOUT_INFORM, BuiltinExchangeType.DIRECT);

            /**
             *  绑定交换机
             *  queue   对列名称
             *  exchange    交换机名称
             *  routingKey  路由Key
             */
            channel.queueBind(QUEUE_INFORM_EMAIL, EXCHANCE_FANOUT_INFORM, QUEUE_INFORM_EMAIL);
            channel.queueBind(QUEUE_INFORM_SMS, EXCHANCE_FANOUT_INFORM, QUEUE_INFORM_SMS);

            String message = "Hello world!";
            channel.basicPublish(EXCHANCE_FANOUT_INFORM, QUEUE_INFORM_SMS, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            if(channel != null){
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }

            if(connection != null){
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}