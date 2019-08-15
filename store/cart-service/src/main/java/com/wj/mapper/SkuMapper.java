package com.wj.mapper;

import com.wj.pojo.Sku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SkuMapper {
    @Select("select * from tb_sku where id=#{id}")
    public Sku selSku(Long id);
}
