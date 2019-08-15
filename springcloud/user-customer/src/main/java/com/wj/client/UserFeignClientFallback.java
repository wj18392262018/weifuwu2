package com.wj.client;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class UserFeignClientFallback implements UserFeignClient {
    @Override
    public String findById(Integer id) {
        return "人之初，性本善@！！";
    }
}
