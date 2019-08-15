package com.wj.contorller;

import com.wj.pojo.Category;
import com.wj.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Controller
@RestController
@RequestMapping("/categroy")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @RequestMapping("/findAll")
    public List<Category> getCategory() {
        List<Category> list=categoryService.fandAll((long)0);
        return list;
    }
}
