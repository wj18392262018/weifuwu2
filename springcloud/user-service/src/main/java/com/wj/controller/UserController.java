package com.wj.controller;

import com.wj.pojo.User;
import com.wj.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private IUserService userService;
    @RequestMapping("/{id}")
    public User getUser(@PathVariable(name = "id") Integer id){
        return userService.getUserById(id);
    }
}
