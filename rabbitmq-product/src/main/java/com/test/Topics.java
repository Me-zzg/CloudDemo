package com.test;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zzg
 * @Description Topics（https://www.rabbitmq.com/tutorials/tutorial-five-java.html）
 *   通配符模式
 *
 *      消费端队列绑定交换机指定通配符：
 *          统配符规则：
 *          中间以“.”分隔。
 *          符号#可以匹配多个词，符号*可以匹配一个词语。
 *
 */
public class Topics {

    private static final String EXCHANCE_FANOUT_INFORM = "exchange_fanout_inform";
    private static final String QUEUE_INFORM_EMAIL = "queue-inform-email";
    private static final String QUEUE_INFORM_SMS = "queue-inform-sms";

    private static final String EMAIL_ROUTINGKEY ="inform.#.email.#";
    private static final String SMS_ROUTINGKEY ="inform.#.sms.#";
    private static final String SMS_EMAIL_ROUTINGKEY ="inform.sms.email";

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
            channel.exchangeDeclare(EXCHANCE_FANOUT_INFORM, BuiltinExchangeType.TOPIC);

            /**
             *  绑定交换机
             *  queue   对列名称
             *  exchange    交换机名称
             *  routingKey  路由Key
             */
            channel.queueBind(QUEUE_INFORM_EMAIL, EXCHANCE_FANOUT_INFORM, EMAIL_ROUTINGKEY);
            channel.queueBind(QUEUE_INFORM_SMS, EXCHANCE_FANOUT_INFORM, SMS_ROUTINGKEY);

            String message = "Hello world!";
            channel.basicPublish(EXCHANCE_FANOUT_INFORM, EMAIL_ROUTINGKEY, null, message.getBytes());
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