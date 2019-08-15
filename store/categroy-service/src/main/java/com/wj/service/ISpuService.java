package com.wj.service;

import com.wj.pojo.Spu;

import java.util.List;

public interface ISpuService {
    public List<Spu> getById(Long cid1, Long cid2, Long cid3,Integer page,Integer size);
}
