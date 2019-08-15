package com.wj;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringCloudApplication //一个标准的cloud消费配置，包含了以上三种注解
//@EnableFeignClients //开启Feign功能
public class WebApplcation {
    public static void main(String[] args) {
        SpringApplication.run(WebApplcation.class);
    }
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }
}
