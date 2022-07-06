package com.mymusic.music.feign;

import com.mymusic.music.domain.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName OrderFeign
 * @Description TODO
 * @Author 86183
 * @Date2022-07-0522:20
 * @Version 1.0
 **/
@FeignClient("music-order")
@Component
public interface OrderFeignClient {
    @GetMapping("/order/getOrder")
    public Order getOrder(@RequestParam("orderNo")String orderNo);
}
