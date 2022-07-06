package com.mymusic.music.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("pay_song")
public class PaySong {
    Long id;
    int type;
    @TableField("user_id")
    Long userId;
    @TableField("song_id")
    Long songId;
    Date create_time;
    Date update_time;
}
