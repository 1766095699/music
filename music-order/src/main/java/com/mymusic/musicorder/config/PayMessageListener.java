package com.mymusic.musicorder.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.music.musiccommon.utils.Query;
import com.mymusic.musicorder.mapper.OrderMapper;
import com.mymusic.myrabbitmq.listener.AbstractMessageListener;
import feign.QueryMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName MyMessageConsumer
 * @Description TODO
 * @Author 86183
 * @Date2022-07-0521:30
 * @Version 1.0
 **/
@Service
@Slf4j
public class PayMessageListener extends AbstractMessageListener {
    @Autowired
    OrderMapper orderMapper;
    @Override
    public void receiveMessage(Message message) {
            log.info("message==={}",message);
            String orderNo = new String(message.getBody());
            orderNo = orderNo.substring(1,orderNo.length()-1 );
        log.info("orderNo==={}",orderNo);
            //更新订单状态为完成
            orderMapper.updateOrder(orderNo);
        }
}
