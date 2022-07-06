package com.mymusic.musicorder.config;

import com.mymusic.musicorder.service.OrderService;
import com.mymusic.myrabbitmq.listener.DelayDlxListener;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

//import com.mymusic.myrabbitmq.listener.CustomDlxQueueListener;

/**
 * @ClassName MyDelayedOrderDlxQueue
 * @Description TODO
 * @Author 86183
 * @Date2022-03-2615:15
 * @Version 1.0
 **/
@Service
@Slf4j
@Primary
public class MyDelayedOrderDlxQueueListener extends DelayDlxListener {
    @Autowired
    private OrderService orderService;
    @Override
    public void receiveMessage(Message message) {
        String orderId = new String(message.getBody());//getBody时取出的数据貌似会自动给你加上双引号
        log.info("处理过期订单中：{}" ,"正在死信队列中删除过期订单");
        //删除过期订单
        orderService.closeOrder(orderId);
    }

    @Override
    public void onMessageBatch(List<Message> list, Channel channel) {

    }
}
