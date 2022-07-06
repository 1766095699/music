package com.music.musicauth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.music.musicauth.entity.Consumer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Aki
 * @Description 用户Dao
 * @Date  2022-06-09
 * @Param 
 * @return 
 **/
@Mapper
public interface ConsumerDao extends BaseMapper<ConsumerDao> {
    public Integer addConsumer(Consumer consumer);

    public Integer deleteConsumerById(Integer id);

    public Integer updateConsumer(Consumer consumer);

    public List<Consumer> getAllConsumer();

    /**
     * 根据主键查询整个对象
     */
    public Consumer selectByPrimaryKey(Integer id);



    /**
     * 验证密码
     */
    public int verifyPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 根据账号查询
     */
    public Consumer getByUsername(String username);
}
