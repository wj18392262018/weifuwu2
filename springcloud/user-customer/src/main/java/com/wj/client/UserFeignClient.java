package com.wj.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "user-service",fallback = UserFeignClientFallback.class)
public interface UserFeignClient {
    @RequestMapping("/user/{id}")
    public String findById(@PathVariable("id") Integer id);
}
