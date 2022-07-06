package com.mymusic.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.mymusic.music.domain.User;
import com.mymusic.music.service.MessageService;
import com.mymusic.music.service.UserService;
import com.mymusic.music.utils.Consts;
import com.mymusic.music.vo.ResponseUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author 86183
 * @Date2021-08-0420:55
 * @Version 1.0
 **/
@RestController
@RequestMapping("/user")
@Api(tags = "用户接口")
//@CrossOrigin(origins = "http://localhost:9876/",maxAge = 3600)
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    MessageService messageService;
//    @ApiOperation(value="判断登录成功与否方法")
//    @PostMapping("/login")
//    public Object userLogin(@RequestBody User user, HttpSession session){
//        JSONObject jsonObject = new JSONObject();
//        User returnUser = userService.userLogin(user.getUsername(),user.getPassword());
//        if(returnUser != null){
//            jsonObject.put(Consts.CODE,1);
//            jsonObject.put(Consts.MSG,"登录成功！");
//            jsonObject.put("user",returnUser);
//            session.setAttribute(Consts.NAME,user.getUsername());
//            return jsonObject;
//        }
//        jsonObject.put(Consts.CODE,0);
//        jsonObject.put(Consts.MSG,"用户名或密码错误！");
//        return jsonObject;
//    }
    @ApiOperation(value="获取好友列表方法")
    @GetMapping("/friends")
    public Object myFriends(@RequestParam("id") int id){
        ArrayList<User> arrayList = userService.getFriends(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data",arrayList);
        return jsonObject;
    }
//    @ApiOperation(value="获取尚未接收其信息的好友列表、未接收的信息条数、最后一条信息方法")
//    @GetMapping("/getNoReceiveUserAndSoOn")
//    public Object getNoReceiveUserAndSoOn(@RequestParam("id") int id){
//        ArrayList<User> usersList = userService.getNoReceiveUser(id);
//        ArrayList<noReceiveUser> arrayList = new ArrayList<noReceiveUser>();
//        for (User user : usersList) {
//            int userId = user.getId();
//            int count = userService.getNoReceiveMessageCounts(userId,id);
//            Message lastMessage = messageService.getLastMessage(userId,id);
//            noReceiveUser noReceiveUser = new noReceiveUser();
//            noReceiveUser.setUser(user);
//            noReceiveUser.setMessage(lastMessage);
//            noReceiveUser.setCount(count);
//            //System.out.println(lastMessage.getSendTime().getClass().toString());//打印取得的时间类型
//            //System.out.println(lastMessage.getSendTime());                      //打印取得的时间
//            //时间转化
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String stringDate = dateFormat.format(lastMessage.getSendTime());
//            //System.out.println(stringDate);//打印转化后的时间
//            noReceiveUser.setSendTime(stringDate);
//            arrayList.add(noReceiveUser);
//        }
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("data",arrayList);
//        return jsonObject;
//    }

    @ApiOperation(value="获取当前用户是否在线")
    @GetMapping("/getStatus")
    public boolean getStatus(@RequestParam("id") int id) {
        return userService.getStatus(id)>0;
    }

    @ApiOperation(value="获取当前用户")
    @GetMapping("/getSingleUser")
    public Object getSingleUser(@RequestParam("nickname") String name) {
        User user = userService.getSingleUser(name);
        JSONObject jsonObject = new JSONObject();
        if(user==null){
            jsonObject.put(Consts.CODE,0 );
            return jsonObject;
        }
        ResponseUser responseUser = new ResponseUser();
        responseUser.setAvatar(user.getAvatar());
        responseUser.setId(user.getId());
        responseUser.setNickname(user.getNickname());
        responseUser.setUsername(user.getUsername());
        System.out.println(user);
        jsonObject.put(Consts.CODE,1 );
        jsonObject.put("data",responseUser);
        return jsonObject;
    }
    @ApiOperation(value="获取当前用户ById")
    @GetMapping("/getUserById")
    public Object getUserById(@RequestParam("id") int id) {
        User user = userService.getUserById(id);
        System.out.println(user);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", user);
        return jsonObject;
    }
}



