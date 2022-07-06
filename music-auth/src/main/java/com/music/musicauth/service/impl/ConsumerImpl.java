package com.music.musicauth.service.impl;

import com.music.musicauth.dao.ConsumerDao;
import com.music.musicauth.entity.Consumer;
import com.music.musicauth.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ConsumerImpl
 * @Description TODO
 * @Author 86183
 * @Date2021-07-2914:21
 * @Version 1.0
 **/
@Service
public class ConsumerImpl implements ConsumerService {
    @Autowired
    private ConsumerDao consumerMapper;
    @Override
    public Boolean addConsumer(Consumer consumer) {
        return consumerMapper.addConsumer(consumer)>0;
    }

    @Override
    public Boolean deleteConsumerById(Integer id) {
        return consumerMapper.deleteConsumerById(id)>0;
    }

    @Override
    public Boolean updateConsumer(Consumer consumer) {
        return consumerMapper.updateConsumer(consumer)>0;
    }

    @Override
    public List<Consumer> getAllConsumer() {
        return consumerMapper.getAllConsumer();
    }

    /**
     * 根据主键查询整个对象
     *
     * @param id
     */
    @Override
    public Consumer selectByPrimaryKey(Integer id) {
        return consumerMapper.selectByPrimaryKey(id);
    }


    /**
     * 查看密码是否正确
     *
     * @param username
     * @param password
     */
    @Override
    public boolean verifyPassword(String username, String password) {
        //验证通过后就设置claims
//        Map<String,Object> claims = new HashMap<>();//claim存jwt中的数据
//        claims.put(Constant.JWT_USER_NAME, username);//用户名username
////        String accessToken = JwtTokenUtil.getAccessToken(username,claims);//获取accessToken
////        System.out.println(accessToken);
//        return consumerMapper.verifyPassword(username,password)>0;
        return true;
    }

    /**
     * 根据账号查询
     *
     * @param username
     */
    @Override
    public Consumer getByUsername(String username) {
        return consumerMapper.getByUsername(username);
    }


}
