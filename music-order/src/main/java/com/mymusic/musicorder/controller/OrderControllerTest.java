

package com.mymusic.musicorder.controller;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.music.musiccommon.bean.Consumer;
import com.mymusic.musicorder.feignclient.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//
///**
// * @ClassName OrderController
// * @Description TODO
// * @Author 86183
// * @Date2022-03-1116:14
// * @Version 1.0
// **/
@RestController
@RequestMapping("/ordertest")
public class OrderControllerTest {
    @Autowired
    private UserFeignClient userFeignClient;
    @GetMapping("/test")
    public Object test1(){
        Object userData  = userFeignClient.selectByPrimaryKey(Integer.valueOf(7));
        System.out.println(userData.getClass());
        String json = JSONObject.toJSONString(userData);
        Consumer userInfoOrder = JSONObject.parseObject(json, new TypeReference<Consumer>() {
        });
        System.out.println(userInfoOrder.getClass());
        return userInfoOrder;
    }
//    @Autowired
//    RedisTemplate redisTemplate;
//
//    @Autowired
//    TransMessageSender transMessageSender;
//    @GetMapping("topic")
//    public String topic(@RequestParam("key")String key){
//        transMessageSender.send("exchangeTopic", key, "hello");
//        return "ok";
//    }
//    @GetMapping("pay")
//    public Object Pay(){
//        String userid = "root_1";
//        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
//        //用lua脚本做原子验证令牌和删除令牌,防止重复下订单
//        Long res = (Long) redisTemplate.execute(new DefaultRedisScript<Long>(script,Long.class), Arrays.asList(userid), "123456");
//        System.out.println(res);
//        return R.ok();
//    }
}
