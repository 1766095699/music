package com.music.musicsongs.controller;

import com.music.musiccommon.utils.R;
import com.music.musicsongs.pojo.Song;
import com.music.musicsongs.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName RankController
 * @Description TODO
 * @Author 86183
 * @Date2022-03-0812:12
 * @Version 1.0
 **/
@RestController
@RequestMapping("/rank")
public class RankController {
    @Autowired
    public SongService songService;
    @Autowired
    public RedisTemplate<String,Object>redisTemplate;
    /**
     * 获取榜单的前10首歌
     * @param id
     * @return
     */

    @GetMapping("/getRankSongOf10/{id}")
    public Object getRankSongOf10(@PathVariable Integer id){
        /**
         * TODO 后面可以加入定时任务做定时读取数据
         */
        List<Song> rankSongOf10 = (List<Song>) redisTemplate.opsForValue().get("rankSongOf10");
        if(rankSongOf10!=null){
            return R.ok(rankSongOf10);
        }
        rankSongOf10 = songService.getRankSongOf10(id);
        redisTemplate.opsForValue().set("rankSongOf10",rankSongOf10 );
        return R.ok(rankSongOf10);
    }
}
