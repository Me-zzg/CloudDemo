package com.test;

import com.config.RabbitConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zzg
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendTest(){
        String message = "Hello RabbitMq~";
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANCE_FANOUT_INFORM, RabbitConfig.EMAIL_ROUTINGKEY, message);

        System.out.println("=============》 Send ok");
    }
}