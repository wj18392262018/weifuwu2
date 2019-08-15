package com.wj.service;

import com.github.pagehelper.PageHelper;
import com.wj.mapper.SkuMapper;
import com.wj.mapper.SpuMapper;
import com.wj.pojo.Spu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("spuService")
public class SpuServiceImpl implements ISpuService {
    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private SkuMapper skuMapper;
    @Override
    public List<Spu> getById(Long cid1, Long cid2, Long cid3,Integer page,Integer size) {
        PageHelper.startPage(page,size);
        List<Spu> list=spuMapper.getById(cid1, cid2, cid3);
        return list;
    }
}
