package com.mymusic.musicpoint.config;

import com.music.musiccommon.bean.Order;
import com.mymusic.musicpoint.feign.ConsumerFeignClient;
import com.mymusic.musicpoint.feign.OrderFeignClient;
import com.mymusic.musicpoint.po.Consumer;
import com.mymusic.myrabbitmq.listener.AbstractMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName MyMessageConsumer
 * @Description TODO
 * @Author 86183
 * @Date2022-07-0521:30
 * @Version 1.0
 **/
@Service
@Slf4j
public class PointPayMessageListener extends AbstractMessageListener {

    @Autowired
    ConsumerFeignClient consumerFeignClient;
    @Autowired
    OrderFeignClient orderFeignClient;
    @Transactional
    @Override
    public void receiveMessage(Message message) {

            log.info("message==={}",message);
            String orderNo = new String(message.getBody());
            orderNo = orderNo.substring(1,orderNo.length()-1 );
            //更新订单状态为完成
        System.out.println("orderNo=="+orderNo);
        System.out.println(orderNo);
        Order order = orderFeignClient.getOrder(orderNo);
        System.out.println("order=="+order);
        Integer userId = Integer.valueOf(order.getMemberId());
        System.out.println("userId=="+userId);
        Consumer singleUser = consumerFeignClient.getSingleUser(userId);
        System.out.println("singleUser"+singleUser);
        singleUser.setPoint(singleUser.getPoint()+1);
        consumerFeignClient.updateUser(singleUser);
        System.out.println("添加积分成功:"+singleUser);


        }
}
