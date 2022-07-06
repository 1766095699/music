package com.mymusic.musicpoint.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

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
    public SimpleMessageListenerContainer payMessageListenerContainer(@Autowired @Qualifier(value = "connectionFactory") ConnectionFactory connectionFactory,
                                                                      PointPayMessageListener pointPayMessageListener) {

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
//        container.setQueues(queue2());
        container.setQueueNames("queue.music.point");
        container.setExposeListenerChannel(true);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(pointPayMessageListener);
        /** 设置消费者能处理消息的最大个数 */
        container.setPrefetchCount(100);
        return container;
    }
    @Bean
    public Queue queue1(){
        HashMap<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", "exchange.dlx");
        arguments.put("x-max-length",10);
        arguments.put("x-dead-letter-routing-key", "key.dlx");//超时的任务扔给order.release.order这个路由键
//        arguments.put("x-message-ttl", 60000); // 消息过期时间 1分钟
        return new Queue("queue.music.point",true,false,false,arguments);
    }
    @Bean
    public Binding binding1(){
        return new Binding("queue.music.point", Binding.DestinationType.QUEUE ,"exchange.music.pay" ,"key.order.pay" ,null);
    }
}
