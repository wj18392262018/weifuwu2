package com.wj.service;

import com.google.gson.Gson;
import com.wj.pojo.Sku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("cartService")
public class CartServiceImpl implements  ICartService{
    @Autowired
    private StringRedisTemplate redisTemplate;
    private Gson gson=new Gson();
    @Override
    //添加
    public void addCart(Sku sku, long userId) {
        String id=userId+"";
        String skuId=sku.getId()+"";
        Integer num=sku.getNum();
        BoundHashOperations options = redisTemplate.boundHashOps(id);
        if(options.hasKey(skuId)){
            sku=gson.fromJson(options.get(skuId).toString(),Sku.class);
            sku.setNum(sku.getNum()+num);
        }
        options.put(skuId,gson.toJson(sku));
    }

    @Override
    //显示
    public List findCart(long userId) {
        List<Sku> clist=new ArrayList<Sku>();
        String id=userId+"";
        BoundHashOperations options = redisTemplate.boundHashOps(id);
        List<String> list=options.values();
        for (String json:list) {
            Sku sku=gson.fromJson(json,Sku.class);
            clist.add(sku);
        }
        return clist;
    }
    @Override
    public Integer delCart(Long userId, Long skuId) {
        redisTemplate.opsForHash().delete(userId+"",skuId+"");
        return 1;
    }

    @Override
    public Integer updateCart(Long userId, Sku sku) {
        BoundHashOperations options = redisTemplate.boundHashOps(userId+"");
        Sku s=gson.fromJson(options.get(sku.getId()+"").toString(),Sku.class);
        s.setNum(sku.getNum());
        System.out.println(sku.getNum());
        options.put(sku.getId().toString(),gson.toJson(s));
        return 1;
    }

    @Override
    public List<Sku> findByCartId(String[] skuIds, String userId) {
        List<Sku> clist=new ArrayList<Sku>();
        BoundHashOperations options = redisTemplate.boundHashOps(userId);
        for (int i=0;i<skuIds.length;i++){
            clist.add(gson.fromJson(options.get(skuIds[i]).toString(),Sku.class));
        }
        return clist;
    }
}
