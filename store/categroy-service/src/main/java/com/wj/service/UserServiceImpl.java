package com.wj.service;

import com.wj.mapper.UserMapper;
import com.wj.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUser(String username, String password) {
        User user=userMapper.getUser(username);
        if(user.getPassword()!=null&&user.getPassword().equals(password)){
            return user;
        }
        return null;
    }
    @Override
    public Integer  addUser(User user) {
        return  userMapper.addUser(user);
    }
}
