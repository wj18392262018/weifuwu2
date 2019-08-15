package com.wj.controller;

import com.google.gson.Gson;
import com.wj.pojo.Sku;
import com.wj.service.ICartService;
import com.wj.service.ISkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ISkuService skuService;
    @Autowired
    private ICartService cartService;
    @RequestMapping("/addCart")
    public void addCart(Long userId,Long skuId,Integer num){
        Sku sku=skuService.selSku(skuId);
        sku.setNum(1);
        cartService.addCart(sku,userId);
    }
    @RequestMapping("/findCart")
    public List findCart(Long userId){
        return cartService.findCart(userId);
    }
    //购物车删除显示
    @RequestMapping("/delCart")
    public Integer delCart(Long userId,Long skuId){
        cartService.delCart(userId,skuId);
        return 1;
    }
    //购物车修改显示
    @RequestMapping("/updateCart")
    public Integer updateCart(Long userId,Long skuId,Integer num){
       Sku sku=new Sku();
       sku.setId(skuId);
       sku.setNum(num);
       return cartService.updateCart(userId,sku);
    }
    @RequestMapping("/findByCartId")
    public String findByCartId(String userId,String skuIds){
        String [] ss=skuIds.split(",");
        List<Sku> list=cartService.findByCartId(ss,userId);
        Gson gson=new Gson();
        String json=gson.toJson(list);
        return json;
    }
}
