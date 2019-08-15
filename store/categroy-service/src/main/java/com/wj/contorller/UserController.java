package com.wj.contorller;

import com.github.pagehelper.PageInfo;
import com.wj.pojo.User;
import com.wj.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @RequestMapping("/getUser")
    public User getUser(String username, String password){
        return userService.getUser(username,password);
    }
}
