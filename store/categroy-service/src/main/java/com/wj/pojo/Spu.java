package com.wj.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Spu implements Serializable {
    private Long id;
    private String title;
    private String spuTitle;
    private Long cid1;
    private Long cid2;
    private Long cid3;
    private Long brandId;
    private Integer saleable;
    private Integer valid;
    private Date createTime;
    private Date lastUpdateTime;
    private List<Sku> skus;
}
