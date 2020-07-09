package com.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zzg
 * @Description
 */
@Configuration
public class RabbitConfig {

    public static final String EXCHANCE_FANOUT_INFORM = "exchange_fanout_inform";

    public static final String QUEUE_INFORM_EMAIL = "queue-inform-email";
    public static final String QUEUE_INFORM_SMS = "queue-inform-sms";

    public static final String EMAIL_ROUTINGKEY ="inform.#.email.#";
    public static final String SMS_ROUTINGKEY ="inform.#.sms.#";
    public static final String SMS_EMAIL_ROUTINGKEY ="inform.sms.email";


    /**
     *  声明交换机
     * @return  Exchange
     */
    @Bean(EXCHANCE_FANOUT_INFORM)
    public Exchange EXCHANCE_FANOUT_INFORM(){
        return ExchangeBuilder.topicExchange(EXCHANCE_FANOUT_INFORM).durable(true).build();
    }

    /**
     *  声明队列
     */
    @Bean(QUEUE_INFORM_EMAIL)
    public Queue QUEUE_INFORM_EMAIL(){
        return new Queue(QUEUE_INFORM_EMAIL);
    }

    @Bean(QUEUE_INFORM_SMS)
    public Queue QUEUE_INFORM_SMS(){
        return new Queue(QUEUE_INFORM_SMS);
    }

    /**
     *  绑定交换机
     */
    @Bean
    public Binding BINDING_QUEUE_INFORM_SMS(@Qualifier(QUEUE_INFORM_SMS) Queue queue,
                                             @Qualifier(EXCHANCE_FANOUT_INFORM) Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(SMS_ROUTINGKEY).noargs();
    }

    @Bean
    public Binding BINDING_QUEUE_INFORM_EMAIL(@Qualifier(QUEUE_INFORM_EMAIL) Queue queue,
                                            @Qualifier(EXCHANCE_FANOUT_INFORM) Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(EMAIL_ROUTINGKEY).noargs();
    }

}