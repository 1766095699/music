package com.mymusic.musicorder.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.music.musiccommon.utils.R;
import com.mymusic.musicorder.domain.Order;
import com.mymusic.musicorder.feignclient.SongFeignClient;
import com.mymusic.musicorder.mapper.OrderMapper;
import com.mymusic.musicorder.service.OrderService;
import com.mymusic.myrabbitmq.sender.TransMessageSender;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-03-13
 */
@Slf4j
@RestController
//@RequestMapping("/order")
@CrossOrigin
public class OrderController{

    @Autowired
    private OrderService orderService;
    @Autowired
    private TransMessageSender transMessageSender;
    @Autowired
    RedissonClient redission;
    @Autowired
    SongFeignClient songFeignClient;
    @Autowired
    private OrderMapper orderMapper;
    @GetMapping("/getOrder")
    public Order getOrder(@RequestParam("orderNo")String orderNo){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("order_no",orderNo );
//        queryWrapper.eq("status",0 );//找出未支付的订单信息!!!
        Order order = orderMapper.selectOne(queryWrapper);
        return order;
    }
    //1 生成订单的方法
    @GetMapping("createOrder/{courseId}/{userId}")
    public R saveOrder(@PathVariable String courseId, HttpServletRequest request,@PathVariable String userId) throws InterruptedException {

        /**
         * 1.先看一下这首歌是否已经买了(不过感觉直接查订单库其实更好点,但是订单库压力就太大了。)
         */
        Boolean havepayed = songFeignClient.havePay(Integer.valueOf(userId),Integer.valueOf(courseId));
        if(havepayed==true){
            return R.error("该歌曲已经被购买");
        }
        /**
         * 2.没买,直接加锁,根据用户id和课程号加锁,防止同一用户重复下单
         */
        RLock lock = redission.getLock((userId.toString()+"_"+courseId).intern());//这里一定要用intern,不然每个string对象地址不同即便值相同
        String orderNo = null;
        boolean isLock = lock.tryLock(10,60,TimeUnit.SECONDS);
        //在获取锁成功后trycatch
        try{
            /**
             * 3.获取分布式锁失败,则直接打回请求
             */
        if(!isLock){
            log.info("分布式锁正在被占有");
            return R.error("不可重复下单");
        }
            /**
             * 获取到分布式锁的线程进行下单操作
             */
        //用redission
        //创建订单，返回订单号
        log.info("获取到了分布式锁:{}",Thread.currentThread().getId() );
        //进入锁的区域后再查一下是否歌曲已经被购买,没有则添加订单,已购买则直接播放歌曲即可
//        havepayed = songFeignClient.havePay(Integer.valueOf(userId),Integer.valueOf(courseId));
//        if(havepayed==true){
//            return R.error("该歌曲已经被购买");
//        }

        //TODO 查询用户是否已经创建了该订单,存在则返回创建订单失败
        //TODO (已解决)这里不知道为什么测试一人一单的时候会出现有两个连续的请求接连获取到锁的情况,tryLock不是应该立刻返回吗
            if(orderService.isExistOrder(userId, courseId)){
                log.info("存在重复下单");
                return R.error("存在重复下单");
            }
            orderNo = orderService.createOrders(courseId, userId);//生成订单号
            //TODO 生成的订单号存进redis,key为用户id
            log.info("订单号="+orderNo);

            //这里要把订单发到死信队列
            transMessageSender.send("exchange.music.order", "key.order", orderNo);//理论上要等消息保存到数据库成功后才能返回才能返回
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();//这里一定要记得解锁

            }
            //TODO这里要靠异步线程去把购买歌曲的具体信息取出来才行
        return R.ok((Object) orderNo);


    }

    //2 根据订单id查询订单信息
    @GetMapping("getOrderInfo/{orderId}")
    public R getOrderInfo(@PathVariable String orderId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderId);
        Order order = orderService.getOne(wrapper);
        return R.ok(order);
    }


}

