package com.mymusic.musicorder.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mymusic.musicorder.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
//    @Update("")
//    @Options(useGeneratedKeys = false)
    Integer closeOrder(@Param("orderNo") String orderNo);

    Integer updateOrder(@Param("orderNo") String orderNo);
}
