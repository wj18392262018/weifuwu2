package com.wj.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Category implements Serializable {
    private Long id;
    private String name;
    private Long parentId;
    private Long isParent;
    private Integer sort;
    private List<Category> categorys;
}
