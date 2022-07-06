package com.mymusic.musicorder.config;

import org.springframework.amqp.core.*;
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

    /**
     * 这里这个queue.music.pay是给订单服务的
     * @return
     */
    @Bean
    public Queue queue2(){
        HashMap<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", "exchange.dlx");
        arguments.put("x-max-length",10);
        arguments.put("x-dead-letter-routing-key", "key.dlx");//超时的任务扔给order.release.order这个路由键
        return new Queue("queue.music.pay",true,false,false,arguments);
    }
    @Bean
    public SimpleMessageListenerContainer payMessageListenerContainer(@Autowired @Qualifier(value = "connectionFactory") ConnectionFactory connectionFactory,
                                                                      PayMessageListener payMessageListener) {

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
//        container.setQueues(queue2());
        container.setQueueNames("queue.music.pay");
        container.setExposeListenerChannel(true);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(payMessageListener);
        /** 设置消费者能处理消息的最大个数 */
        container.setPrefetchCount(100);
        return container;
    }
    @Bean
    public TopicExchange DelaydlxExchange() {
        return new TopicExchange("exchange.orderDelay.dlx");
    }

    @Bean
    public Queue DelayddlxQueue() {
        return new Queue("queue.orderDelay.dlx",true,false,false);
    }

    @Bean
    public Binding DelayddlxBinding() {
        return BindingBuilder.bind(DelayddlxQueue()).to(DelaydlxExchange())
                .with("#");
    }


    @Bean
    public Exchange exchange1(){
        return new DirectExchange("exchange.music.order");
    }
    @Bean
    public Queue queue1(){
        HashMap<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", "exchange.orderDelay.dlx");
        arguments.put("x-max-length",10);
        arguments.put("x-dead-letter-routing-key", "key.delayedorder.dlx");//超时的任务扔给order.release.order这个路由键
        arguments.put("x-message-ttl", 60000); // 消息过期时间 1分钟
        return new Queue("queue.music.order",true,false,false,arguments);
    }
    @Bean
    public Binding binding1(){
        return new Binding("queue.music.order", Binding.DestinationType.QUEUE ,"exchange.music.order" ,"key.order" ,null );
    }
    @Bean
    public Binding binding2(){
        return new Binding("queue.music.pay", Binding.DestinationType.QUEUE ,"exchange.music.pay" ,"key.order.pay" ,null );
    }


    @Bean
    public SimpleMessageListenerContainer deadLetterListenerContainer_DaylerOrder(ConnectionFactory connectionFactory,
                                                                                  MyDelayedOrderDlxQueueListener dlxListener) {

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(DelayddlxQueue());
        container.setExposeListenerChannel(true);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(dlxListener);
        /** 设置消费者能处理消息的最大个数 */
        container.setPrefetchCount(100);
        return container;
    }
}
