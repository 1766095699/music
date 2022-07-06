package com.music.thirdpart.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RabbitMqInit
 * @Description TODO
 * @Author 86183
 * @Date2022-03-2422:55
 * @Version 1.0
 **/
@Configuration
public class RabbitMqInit {
    @Bean
    public Exchange exchange1(){
        return new DirectExchange("exchange.music.pay");
    }


//    @Bean
//    public Binding binding1(){
//        return new Binding("queue.music.pay", Binding.DestinationType.QUEUE ,"exchange.music.pay" ,"key.order.pay" ,null);
//    }
}
