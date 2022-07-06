package com.music.musicauth.config;

/**
 * @ClassName MyMetaObjectHandler
 * @Description TODO
 * @Author 86183
 * @Date2022-07-0516:35
 * @Version 1.0
 **/
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * @Date: 2022/4/30 11:25
 * @Author: 小王
 * @Description: 自动填充实现类
 */
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }


}

