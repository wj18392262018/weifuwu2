package com.wj.mapper;

import com.wj.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Select("SELECT * FROM tb_category WHERE parent_id=#{parentId}")
    public List<Category> fandAll(Long parentId);
}
