package com.wj.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrderStatus implements Serializable {
    private Long orderId;//订单id
    private Integer status;//状态：1、未付款 2、已付款,未发货 3、已发货,未确认 4、交易成功 5、交易关闭 6、已评价
    private Date createTime;//订单创建时间
    private Date paymentTime;//订单创建时间
    private Date consignTime;//发货时间
    private Date endTime;//交易完成时间
    private Date closeTime;//交易关闭时间
    private Date commentTime;//评价时间

    public OrderStatus() {
    }

    public OrderStatus(Long orderId, Integer status, Date createTime) {
        this.orderId = orderId;
        this.status = status;
        this.createTime = createTime;
    }
}
