//package com.mymusic.music.xxl_job_task;
//
//import com.mymusic.music.domain.SongList;
//import com.mymusic.music.service.SongListService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
///**
// * @ClassName MyXxlJob
// * @Description TODO
// * @Author 86183
// * @Date2022-03-1618:02
// * @Version 1.0
// **/
//@Component
//@Slf4j
//public class MyXxlJob {
//    @Autowired
//    private SongListService songListService;
//    @Autowired
//    RedisTemplate<String,Object> redisTemplate;
//    @XxlJob("myJobHandler")
//    public ReturnT<String> execute(String param){
//        int t = XxlJobHelper.getShardIndex()/getShardTotal();//获取分片号
//        log.info("myXXLJOB--------------------"+t);
////        List<SongList> songLists = songListService.RandomSongList();
////        redisTemplate.opsForValue().set("songList", songLists );
////        return ReturnT.SUCCESS;
//    }
//}
