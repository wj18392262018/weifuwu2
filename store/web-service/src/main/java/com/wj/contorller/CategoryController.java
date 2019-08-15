package com.wj.contorller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.wj.pojo.Category;
import com.wj.pojo.Sku;
import com.wj.pojo.Spu;
import com.wj.pojo.User;
import com.wj.util.PayDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PayDemo payDemo;
    //显示主页
    @RequestMapping("/findAll")
    public String getUser(ModelMap model){
        String url="http://categroy-service"+"/categroy/findAll";
        List<Category> clist=restTemplate.getForObject(url, List.class);
        model.put("clist",clist);
        return "index";
    }
    //显示手机页
    @RequestMapping("/getById")
    public String getById(Long cid1, Long cid2, Long cid3,Integer page,Integer size,ModelMap model){
        String url="http://categroy-service/spu/getById?cid1="+cid1+"&&cid2="+cid2+"&&cid3="+cid3+"&&page="+page+"&&size="+size;
        PageInfo pageInfo=restTemplate.getForObject(url, PageInfo.class);
        model.put("pageInfo",pageInfo);
        model.put("cid1",cid1);
        model.put("cid2",cid2);
        model.put("cid3",cid3);
        return "search";
    }
    //去登陆页面
    @RequestMapping("/login")
    public String getLog(){
        return "login";
    }
    //去注册页面
    @RequestMapping("/register")
    public String getRegister(){
        return "register";
    }
    //注册
    @RequestMapping("/addUser")
    public String logUser(User user, HttpSession session){
        Gson gson=new Gson();
        user.setCreated(new Date());
        user.setId((long)0);
        String json=gson.toJson(user);
        String url="http://categroy-service"+"/register/addUser?json="+json;
        Integer i=restTemplate.getForObject(url, Integer.class,json);
        if(i>0){
            return "redirect:login";
        }else {
            return "redirect:register";
        }
    }
    //登陆
    @RequestMapping("/logUser")
    public String logUser(String username,String password,ModelMap model,HttpSession session){
        String url="http://categroy-service"+"/user/getUser?username="+username+"&&password="+password;
        User user=restTemplate.getForObject(url, User.class);
        session.setAttribute("uname",user.getUsername());
        session.setAttribute("uid",user.getId());
        if(user!=null){
            return "redirect:findAll";
        }else {
            return "redirect:login";
        }
    }
    //购物车添加
    @RequestMapping("/addCart")
    public String addCart(Long userId,Long skuId,Integer num){
        String url="http://cart-service"+"/cart/addCart?userId="+userId+"&&skuId="+skuId+"&&num="+num;
        Object o=restTemplate.getForObject(url, Object.class);
        return "redirect:findCart?userId="+userId;
    }
    //购物车跳转显示
    @RequestMapping("/findCart")
    public String findCart(Long userId,ModelMap model){
        String url="http://cart-service"+"/cart/findCart?userId="+userId;
        Sku[] list=restTemplate.getForObject(url, Sku[].class);
        long zong=0;
        for (int i = 0; i <list.length ; i++) {
            zong+=list[i].getPrice()*list[i].getNum();
        }
        model.put("number",list.length);
        model.put("zong",zong);
        model.put("list",list);
        return "cart";
    }
    //购物车删除显示
    @RequestMapping("/delCart")
    public String delCart(Long userId,Long skuId,ModelMap model){
        String url="http://cart-service"+"/cart/delCart?userId="+userId+"&&skuId="+skuId;
        Integer i=restTemplate.getForObject(url, Integer.class);
        return "redirect:findCart?userId="+userId;
    }
    //购物车修改显示
    @RequestMapping("/updateCart")
    public String updateCart(Long userId,Long skuId,Integer num){
        String url="http://cart-service"+"/cart/updateCart?userId="+userId+"&&skuId="+skuId+"&&num="+num;
        Integer i=restTemplate.getForObject(url, Integer.class);
        return "redirect:findCart?userId="+userId;
    }
    //跳转订单页
    @RequestMapping("/findOrder")
    public String findOrder(String skuIds,HttpSession session,ModelMap model){
        Long userId=(Long)session.getAttribute("uid");
        String url="http://cart-service"+"/cart/findByCartId?userId="+userId+"&&skuIds="+skuIds;
        String json=restTemplate.getForObject(url, String.class);
        Gson gson=new Gson();
        Sku[] skus=gson.fromJson(json,Sku[].class);
        long zong=0;
        for (int i = 0; i <skus.length ; i++) {
            zong+=skus[i].getPrice()*skus[i].getNum();
        }
        model.put("zong",zong);
        model.put("list",skus);
        model.put("number",skus.length);
        return "getOrderInfo";
    }
    //提交订单
    @RequestMapping("/submitOrder")
    public String submitOrder(String userId,Integer zifu,String skuId,ModelMap model){
        //得到总价
        Gson gson=new Gson();
        String url="http://cart-service"+"/cart/findByCartId?userId="+userId+"&&skuIds="+skuId;
        String json=restTemplate.getForObject(url, String.class);
        Sku[] skus=gson.fromJson(json,Sku[].class);
        long zong=0;
        for (int i = 0; i <skus.length ; i++) {
            zong+=skus[i].getPrice()*skus[i].getNum();
        }
        String url1="http://order-service"+"/order/submitOrder?zong="+zong+"&&zifu="+zifu+"&&userId="+userId+"&&skus="+json;
        String  orderId=restTemplate.getForObject(url1, String.class);
        String dd=payDemo.testPay(orderId);
        model.put("code",dd);
        model.put("orderId",orderId);
        model.put("zong",zong);
        return "pay";
    }
    @RequestMapping("findPayDemo")
    @ResponseBody
    public void method1(String orderId, HttpServletResponse response) {
        String xinxi=payDemo.queryPay(orderId);
        System.out.println(orderId);
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        // 返回结果
        try {
            response.getWriter().print(xinxi);
        }catch (Exception e){

        }
    }
}
