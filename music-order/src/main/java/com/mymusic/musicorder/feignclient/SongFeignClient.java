package com.mymusic.musicorder.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName SongFeignClient
 * @Description TODO
 * @Author 86183
 * @Date2022-07-0512:13
 * @Version 1.0
 **/
@FeignClient(value = "music-server2",url = "localhost:9090")
@Component
public interface SongFeignClient {
    @RequestMapping(value = "/song/songDetail",method = RequestMethod.GET)
    public Object detail(@RequestParam("songId") Integer songId);

    @RequestMapping(value = "/payedSong/havePayed",method = RequestMethod.GET)
    public Boolean havePay(@RequestParam("userId") Integer userId,@RequestParam("songId")Integer songId);
}
