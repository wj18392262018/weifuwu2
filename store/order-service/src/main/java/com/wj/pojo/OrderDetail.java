package com.wj.pojo;

import lombok.Data;

import java.io.Serializable;
@Data
public class OrderDetail implements Serializable {
    private Long id;//订单详情id
    private Long orderId;//订单id
    private Long skuId;//sku商品id
    private Integer num;//购买数量
    private String title;//商品标题
    private String ownSpec;//商品动态属性键值集
    private Long price;//价格,单位：分
    private String image;//商品图片

    public OrderDetail() {
    }

    public OrderDetail(Long orderId, Long skuId, Integer num, String title, Long price) {
        this.orderId = orderId;
        this.skuId = skuId;
        this.num = num;
        this.title = title;
        this.price = price;
    }
}
