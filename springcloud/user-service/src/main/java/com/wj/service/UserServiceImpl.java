package com.wj.service;

import com.wj.mapper.UserMapper;
import com.wj.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service("userService")
public class UserServiceImpl implements IUserService {
   @Autowired
   private UserMapper userMapper;
    @Override
    public User getUserById(Integer id) {
        /*模拟熔断时间*/
        /*try {
            Thread.sleep(new Random().nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return userMapper.selectByPrimaryKey(id);
    }
}
