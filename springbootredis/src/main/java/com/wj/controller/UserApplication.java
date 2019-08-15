package com.wj.controller;

import com.wj.service.IUservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApplication {
    @Autowired
    private IUservice uservice;
    @RequestMapping("/user")
    public String getUser(){
        return uservice.getUser();
    }
}
