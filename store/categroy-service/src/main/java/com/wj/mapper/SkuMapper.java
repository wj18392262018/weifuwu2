package com.wj.mapper;

import com.wj.pojo.Sku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SkuMapper {
    @Select("select * from tb_sku where spu_id=#{spuId}")
    public List<Sku> getById(Long spuId);
}
