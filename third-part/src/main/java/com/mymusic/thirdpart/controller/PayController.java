package com.mymusic.thirdpart.controller;

import com.alibaba.fastjson.JSONObject;
import com.mymusic.myrabbitmq.sender.TransMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName PayController
 * @Description TODO
 * @Author 86183
 * @Date2022-03-1916:58
 * @Version 1.0
 **/
@RestController
@RequestMapping("/payment")
//@CrossOrigin
public class PayController {
    @Autowired
    TransMessageSender transMessageSender;
    @GetMapping("/pay")
    public Object pay(@RequestParam("orderNo")String orderNo){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",200 );
        jsonObject.put("msg","支付成功");
        System.out.println("111");
        transMessageSender.send("exchange.music.pay", "key.order.pay",orderNo);
        return jsonObject;
    }

}
