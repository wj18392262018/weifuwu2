package com.wj.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class Sku  implements Serializable {
    private Long id;
    private Long spuId;
    private String title;
    private String images;
    private Long price;
    private String indexes;
    private String ownSpac;
    private Integer enable;
    private Date createTime;
    private Date lastUpdateTime;
    private Integer num;
}
