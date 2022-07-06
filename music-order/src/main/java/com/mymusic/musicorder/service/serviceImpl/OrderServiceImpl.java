package com.mymusic.musicorder.service.serviceImpl;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.music.musiccommon.bean.Consumer;
import com.music.musiccommon.bean.Song;
import com.music.musiccommon.utils.GsonUtil;
import com.mymusic.musicorder.domain.Order;
import com.mymusic.musicorder.feignclient.SongFeignClient;
import com.mymusic.musicorder.feignclient.UserFeignClient;
import com.mymusic.musicorder.mapper.OrderMapper;
import com.mymusic.musicorder.service.OrderService;
import com.mymusic.musicorder.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.mymusic.musicorder.feignclient.SongFeignClient;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-03-13
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private SongFeignClient songService;

    @Autowired
    private UserFeignClient userService;
    @Autowired
    private OrderMapper orderMapper;
    //1 生成订单的方法
    @Override
    public String createOrders(String courseId, String memberId) {

        //通过远程调用根据用户id获取用户信息
        Object userData  = userService.selectByPrimaryKey(Integer.valueOf(memberId));
        String json = GsonUtil.simpleObjToJson(userData);
//        System.out.println("json=="+json);
        Consumer userInfoOrder = GsonUtil.simpleJsonToObj(json,Consumer.class);

//        Consumer userInfoOrder = JSONObject.parseObject(json,Consumer.class);
        //通过远程调用根据课程id获取课信息
//        System.out.println("user=="+userInfoOrder);
        Object songData=  songService.detail(Integer.valueOf(courseId));
        json = JSONObject.toJSONString(songData);
//        System.out.println(json);
        Song songInfo = GsonUtil.simpleJsonToObj(json,Song.class);
        //创建Order对象，向order对象里面设置需要数据
        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());//订单号
        order.setCourseId(courseId); //课程id
        order.setCourseTitle(songInfo.getName().split("-")[1]);
        order.setCourseCover(songInfo.getPic());
        order.setTeacherName(songInfo.getName().split("-")[0]);
        order.setTotalFee(songInfo.getPrice());
        order.setMemberId(memberId);
//        order.setMobile(userInfoOrder.getPhoneNum());
//        order.setNickname(userInfoOrder.ge());
        order.setStatus(0);  //订单状态（0：未支付 1：已支付）
        order.setPayType(1);  //支付类型 ，微信1
//        baseMapper.select
        baseMapper.insert(order);
         //返回订单号
        return order.getOrderNo();
    }

    @Override
    public Integer closeOrder(String orderId) {
        System.out.println("order信息为："+orderId.substring(1, orderId.length()-1));
        orderId = orderId.substring(1, orderId.length()-1);
        QueryWrapper<Order>queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no",orderId);
        System.out.println("order的详细信息:"+baseMapper.selectList(queryWrapper));
        return orderMapper.closeOrder(orderId);//这里我直接统一把过期未删除的订单全部逻辑删除了
    }

    @Override
    public boolean isExistOrder(String userid,String songid){
        QueryWrapper<Order>queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",songid);
        queryWrapper.eq("member_id",userid);
        int cnt = baseMapper.selectCount(queryWrapper);
        if(cnt==0){
            return false;
        }
        return true;
    }


}
