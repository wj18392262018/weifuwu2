package com.wj.mapper;

import com.wj.pojo.OrderStatus;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderStatusMapper {
    @Insert("insert into tb_order_status (order_id,status,create_time)values(#{orderStatus.orderId},#{orderStatus.status},#{orderStatus.createTime})")
    public Integer addOrderStatusMapper(@Param("orderStatus") OrderStatus orderStatus);
}
