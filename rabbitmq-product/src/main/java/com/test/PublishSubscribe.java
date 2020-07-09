package com.test;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zzg
 * @Description 发布/订阅 Publish/Subscribe（https://www.rabbitmq.com/tutorials/tutorial-three-java.html）
 * 本质上，已发布的日志消息将被广播到所有接收者。
 *
 *      发布订阅模式：
 *         1、每个消费者监听自己的队列。
 *         2、生产者将消息发给broker，由交换机将消息转发到绑定此交换机的每个队列，每个绑定交换机的队列都将接收到消息
 *
 */
public class PublishSubscribe {

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
            channel.exchangeDeclare(EXCHANCE_FANOUT_INFORM, BuiltinExchangeType.FANOUT);

            /**
             *  绑定交换机
             *  queue   对列名称
             *  exchange    交换机名称
             *  routingKey  路由Key
             */
            channel.queueBind(QUEUE_INFORM_EMAIL, EXCHANCE_FANOUT_INFORM, "");
            channel.queueBind(QUEUE_INFORM_SMS, EXCHANCE_FANOUT_INFORM, "");

            String message = "Hello world!";
            channel.basicPublish(EXCHANCE_FANOUT_INFORM, "", null, message.getBytes());
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