package com.wj.mapper;

import com.wj.pojo.OrderDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDetailMapper {
    @Insert("insert into tb_order_detail (order_id,sku_id,num,title,price)value(#{orderDetail.orderId},#{orderDetail.skuId},#{orderDetail.num},#{orderDetail.title},#{orderDetail.price})")
    public Integer addOrderDetail(@Param("orderDetail") OrderDetail orderDetail);
}
