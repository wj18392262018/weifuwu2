package com.wj.service;

import com.wj.mapper.CategoryMapper;
import com.wj.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public List<Category> fandAll(Long pid) {
        List<Category> list1=categoryMapper.fandAll(pid);
        for(Category c:list1){
            Long cId = c.getId();
            List<Category> list2 = categoryMapper.fandAll(cId);
            c.setCategorys(list2);

            for(Category c2: list2){
                Long cId2 = c2.getId();
                List<Category> list3 = categoryMapper.fandAll(cId2);
                c2.setCategorys(list3);

            }
        }
        return list1;
    }
}
