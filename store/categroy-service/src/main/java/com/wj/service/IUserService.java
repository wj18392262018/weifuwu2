package com.wj.service;

import com.wj.pojo.User;

public interface IUserService {
    public User getUser(String username,String password);
    public Integer addUser(User user);
}
