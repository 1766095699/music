package com.mymusic.musicorder.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName UserFeignClient
 * @Description TODO
 * @Author 86183
 * @Date2022-07-0512:13
 * @Version 1.0
 **/
@FeignClient(value = "music-auth",url = "localhost:13000")

//@RequestMapping("")
public interface UserFeignClient {
    //如果你是要做参数拼接,这里一定要加@RequestParam
    @RequestMapping(value = "/auth/consumer/getUserById",method = RequestMethod.GET)
    public Object selectByPrimaryKey(@RequestParam("userId") Integer userId);
}
