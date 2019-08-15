package com.wj.mapper;

import com.wj.pojo.Spu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SpuMapper {
    @Select("select * from tb_spu where cid1=#{cid1} and cid2=#{cid2} and cid3=#{cid3}" )
    public List<Spu> getById(Long cid1,Long cid2,Long cid3);
}