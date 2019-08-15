package com.wj.service;

import com.wj.mapper.SkuMapper;
import com.wj.pojo.Sku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("skuService")
public class SkuServiceImpl implements ISkuService {
    @Autowired
    private SkuMapper skuMapper;
    @Override
    public Sku selSku(Long id) {
        return skuMapper.selSku(id);
    }
}
