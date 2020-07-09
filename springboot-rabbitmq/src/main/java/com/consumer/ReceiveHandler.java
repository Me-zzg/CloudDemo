package com.consumer;

import com.config.RabbitConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author zzg
 * @Description
 */
@Component
public class ReceiveHandler {

    @RabbitListener(queues = {RabbitConfig.QUEUE_INFORM_SMS})
    public void receive_sms(String msg, Message message, Channel channel){
        System.out.println("[receive_sms]==============》" + msg);
    }

    @RabbitListener(queues = {RabbitConfig.QUEUE_INFORM_EMAIL})
    public void receive_email(String msg, Message message, Channel channel){
        System.out.println("[receive_email]==============》" + msg);
    }

}