package com.mymusic.music.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 歌曲
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Song implements Serializable {
    /*主键*/
    private Integer id;
    //歌手id
    private Integer singerId;
    /*歌名*/
    private String name;
    /*简介*/
    private String introduction;
    /*创建时间*/
    private Date createTime;
    /*更新时间*/
    private Date updateTime;
    /*歌曲图片*/
    private String pic;
    /*歌词*/
    private String lyric;
    /*歌曲地址*/
    private String url;
    /*mv地址*/
    private String mvurl;
    //价格
    private BigDecimal price;
    private Long count;
    @TableField("buy_count")
    private Long buyCount;
}
