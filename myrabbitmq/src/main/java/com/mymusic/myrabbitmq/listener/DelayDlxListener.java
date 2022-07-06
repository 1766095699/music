package com.mymusic.myrabbitmq.listener;

import com.mymusic.myrabbitmq.service.TransMessageService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@ConditionalOnProperty("rb.dlxEnabled")
@Slf4j
public abstract class DelayDlxListener implements ChannelAwareMessageListener {

    @Autowired
    TransMessageService transMessageService;

    /**
     * 模板方法模式。重写该方法来执行消费死信消息的业务
     * @param message
     * @throws Exception
     */
    public abstract void receiveMessage(Message message) throws Exception;
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        String messageBody = new String(message.getBody());
        log.error("dead letter message：{} | tag：{}", messageBody, message.getMessageProperties().getDeliveryTag());
        log.info("进入DelayDlxListener中消费死信消息" );
        //         发邮件
        //        sendEmail(logKey, messageProperties.getMessageId(), messageBody);
        log.error("sendEmail");
        receiveMessage(message);
        //TODO 这里直接删除订单即可,其实不用入库,我只是做一下记录
        transMessageService.messageDead(
                message.getMessageProperties().getMessageId(),
                message.getMessageProperties().getReceivedExchange(),
                message.getMessageProperties().getReceivedRoutingKey(),
                message.getMessageProperties().getConsumerQueue(),
                messageBody);

        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
