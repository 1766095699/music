package com.mymusic.musicpoint.feign;

import com.mymusic.musicpoint.po.Consumer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName ConsumerFeignClient
 * @Description TODO
 * @Author 86183
 * @Date2022-07-0523:00
 * @Version 1.0
 **/
@FeignClient(value = "music-auth",url = "localhost:13000")
@Component
public interface ConsumerFeignClient {
    @GetMapping("/auth/consumer/getSingleUser")
    public Consumer getSingleUser(@RequestParam("userId") Integer userId);
    @PostMapping("/auth/consumer/updateUser")
    public Boolean updateUser(@RequestBody Consumer consumer);
}
