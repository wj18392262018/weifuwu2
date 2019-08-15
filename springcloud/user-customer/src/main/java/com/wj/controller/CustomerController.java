package com.wj.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wj.client.UserFeignClient;
import com.wj.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/customer")
@DefaultProperties(defaultFallback = "defaultFallback") //指定所有方法的默认回调方法
public class CustomerController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;//获取注册链表
  /*  @RequestMapping("/{id}")
    public User getUser(@PathVariable Integer id){
      *//*  List<ServiceInstance> instances = discoveryClient.getInstances("user-service");//从注册中心的到user-service
        ServiceInstance instance = instances.get(0);
        String url="http://"+instance.getHost()+":"+instance.getPort()+"/user/"+id;
        *//*
      String url="http://user-service/user/"+id;
      return restTemplate.getForObject(url, User.class);
    }*/
    //@HystrixCommand
    //@HystrixCommand(fallbackMethod = "queryByIdFallback") //声明一个失败回滚处理函数
   /*如果一些特殊的业务需要调整hystrix的超时时间，通过以下注解可以完成：*/
    /*@HystrixCommand(fallbackMethod = "queryByIdFallback",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value="3000")
    }) //声明一个失败回滚处理函数*/
   /* @RequestMapping("/{id}")
   @HystrixCommand(fallbackMethod = "queryByIdFallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value="10"),//断路器请求次数
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value="60"),//失败比例
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value="10000")//休眠时间
    })
    public String getUser(@PathVariable Integer id){
        if(id%2==0)
            throw  new RuntimeException("请求失败！");
        String url="http://user-service/user/"+id;
        return restTemplate.getForObject(url, String.class);
    }*/
   @Autowired
    private UserFeignClient userFeignClient;//使用feign
    @RequestMapping("/{id}")
    /*@HystrixCommand(fallbackMethod = "queryByIdFallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value="10"),//断路器请求次数
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value="60"),//失败比例
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value="10000")//休眠时间
    })*/
    public String getUser(@PathVariable Integer id){
        /*if(id%2==0)
            throw  new RuntimeException("请求失败！");*/
        return userFeignClient.findById(id);
    }
    //此方法要求：与queryById()方法的参数和返回类型都必须一致，为了方便返回消息所有对原来的方法做改造。
    public String queryByIdFallback(Integer id){
        return "不好意思，服务器太忙了~";
    }
    public String defaultFallback(){ //该方法没有参数，是所有方法的回调方法，一般返回String
        return "不好意思，网络走丢了~~~";
    }
}
