package com.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zzg
 * @Description 工作队列 Work Queue (https://www.rabbitmq.com/tutorials/tutorial-two-java.html)
 *（又名：任务队列）的主要思想是避免立即执行资源密集型任务，而不得不等待它完成。
 * 相反，我们安排任务在以后完成。我们将任务封装 为消息并将其发送到队列。
 * 在后台运行的工作进程将弹出任务并最终执行作业。当您运行许多工作人员时，任务将在他们之间共享。
 *
 *
 * Work queues与入门程序相比，多了一个消费端，两个消费端共同消费同一个队列中的消息。应用场景：对于 任务过重或任务较多情况使用工作队列可以提高任务处理的速度。
 *
 *      测试：
 *        1、使用入门程序，启动多个消费者。
 *        2、生产者发送多个消息。
 *      结果：
 *        1、一条消息只会被一个消费者接收；
 *        2、rabbit采用轮询的方式将消息是平均发送给消费者的；
 *        3、消费者在处理完某条消息后，才会收到下一条消息。
 *
 */


public class WorkQueue {

    private static final String QUEUE_NAME = "work-queue";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = null;
        Channel channel= null;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello world!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
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