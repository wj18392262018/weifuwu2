package com.wj.service;

import com.wj.mapper.SkuMapper;
import com.wj.pojo.Sku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("skuService")
public class SkuServiceImpl implements  ISkuService {
    @Autowired
    private SkuMapper skuMapper;
    @Override
    public List<Sku> getById(Long spuId) {
        return skuMapper.getById(spuId);
    }
}
