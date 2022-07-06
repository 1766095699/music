package com.mymusic.myrabbitmq.config;

import org.springframework.context.annotation.Configuration;

@Configuration
//@ConditionalOnProperty("rb.dlxEnabled")
public class OrderDelayDlxConfig {
//    @Bean
//    public TopicExchange dlxExchange() {
//        return new TopicExchange("exchange.orderDelay.dlx");
//    }
//
//    @Bean
//    public Queue dlxQueue() {
//        return new Queue("queue.orderDelay.dlx",true,false,false);
//    }
//
//    @Bean
//    public Binding dlxBinding() {
//        return BindingBuilder.bind(dlxQueue()).to(dlxExchange())
//                .with("#");
//    }

//    @Bean
//    public SimpleMessageListenerContainer deadLetterListenerContainer(ConnectionFactory connectionFactory,
//                                                                      DlxListener dlxListener) {
//
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
//        container.setQueues(dlxQueue());
//        container.setExposeListenerChannel(true);
//        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//        container.setMessageListener(dlxListener);
//        /** 设置消费者能处理消息的最大个数 */
//        container.setPrefetchCount(100);
//        return container;
//    }

}
