package com.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zzg
 * @Description
 */
public class SendHello {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            /**
             * queue    队列名称
             * durable  如果我们声明了一个持久队列(该队列将在服务器重启后继续存在)
             * exclusive    如果声明排他队列(仅限于此连接)，则为true
             * autoDelete   true，如果我们声明一个自动删除队列(服务器将删除它时，不再使用)
             * arguments    队列的其他属性(构造参数)
             */
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello world!";

            /**
             * exchange 交换机；如果没有指定，则使用Default Exchange
             * routingKey   路由Key 唯一标识；是用于Exchange（交换机）将消息转发到指定的消息队列
             * props    其他属性的消息-路由头等；消息包含的属性
             * body     消息体
             */
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}