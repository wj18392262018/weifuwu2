package com.wj.service;

import com.wj.entity.User;
import com.wj.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements IUservice {
   /*SpringBoot 中Redsi的操作类*/
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserMapper userMapper;
    public void setUser() {
        User user=userMapper.selectByPrimaryKey(2);
        stringRedisTemplate.opsForValue().set("user",user.toString());
    }
    @Override
    public String getUser() {
        this.setUser();
        return stringRedisTemplate.opsForValue().get("user");
    }
}
