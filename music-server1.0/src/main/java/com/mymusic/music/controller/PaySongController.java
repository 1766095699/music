package com.mymusic.music.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mymusic.music.dao.PaySongMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName PaySongController
 * @Description TODO
 * @Author 86183
 * @Date2022-07-0516:44
 * @Version 1.0
 **/
@RestController
@RequestMapping("/payedSong")
public class PaySongController {
    @Autowired
    PaySongMapper paySongMapper;
    @GetMapping("/havePayed")
    public Boolean havePay(@RequestParam("userId") Integer userId,@RequestParam("songId")Integer songId){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",userId );
        queryWrapper.eq("song_id",songId );
        Integer count = paySongMapper.selectCount(queryWrapper);
        if(count>0){
            return true;
        }
        return false;
    }
}
