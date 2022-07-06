package com.mymusic.music.config;

import com.mymusic.music.dao.PaySongMapper;
import com.mymusic.music.dao.SongMapper;
import com.mymusic.music.domain.Order;
import com.mymusic.music.domain.PaySong;
import com.mymusic.music.feign.OrderFeignClient;
import com.mymusic.myrabbitmq.listener.AbstractMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @ClassName MyMessageConsumer
 * @Description TODO
 * @Author 86183
 * @Date2022-07-0521:30
 * @Version 1.0
 **/
@Service
@Slf4j
public class SongPayMessageListener extends AbstractMessageListener {
    @Autowired
    SongMapper songMapper;
    @Autowired
    OrderFeignClient orderFeignClient;
    @Autowired
    PaySongMapper paySongMapper;
    @Override
    public void receiveMessage(Message message) {
            log.info("message==={}",message);
        byte[] body = message.getBody();
        String orderNo = new String(body);
        orderNo = orderNo.substring(1,orderNo.length()-1 );
            //更新订单状态为完成
        System.out.println("orderNO=="+orderNo);
        Order order = orderFeignClient.getOrder(orderNo);
        if(Objects.isNull(order)){

        }else{//能获取到未支付订单
            System.out.println("order=="+order);
            PaySong paySong = new PaySong();
            paySong.setSongId(Long.valueOf(order.getCourseId()));
            paySong.setUserId(Long.valueOf(order.getMemberId()));
            paySongMapper.insert(paySong);
        }



        }
}
