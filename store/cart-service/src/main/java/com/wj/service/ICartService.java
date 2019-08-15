package com.wj.service;

import com.wj.pojo.Sku;

import java.util.List;

public interface ICartService {
    public void addCart(Sku sku, long userId);
    public List findCart(long userId);
    public Integer delCart(Long userId, Long skuId);
    public Integer updateCart(Long userId, Sku sku);
    public List<Sku> findByCartId(String[] skuIds, String userId);
}
