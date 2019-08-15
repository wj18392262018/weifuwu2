package com.wj.mapper;

import com.wj.pojo.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {
    @Insert("insert into tb_order (order_id,total_pay,actual_pay,payment_type,create_time,user_id)values(#{order.orderId},#{order.totalPay},#{order.actualPay},#{order.paymentType},#{order.createTime},#{order.userId})")
    public Integer addOrder(@Param("order") Order order);
}
