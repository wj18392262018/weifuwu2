package com.wj.service;

import com.wj.pojo.Sku;

import java.util.List;

public interface ISkuService {
    public List<Sku> getById(Long spuId);
}
