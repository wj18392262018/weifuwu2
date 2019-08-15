package com.wj.contorller;

import com.google.gson.Gson;
import com.wj.pojo.User;
import com.wj.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private IUserService userService;
    @RequestMapping("/addUser")
    public Integer addUser(String json){
        Gson gson=new Gson();
        System.out.println(json);
        User user=gson.fromJson(json,User.class);
        System.out.println(user);
        return  userService.addUser(user);
    }
}
