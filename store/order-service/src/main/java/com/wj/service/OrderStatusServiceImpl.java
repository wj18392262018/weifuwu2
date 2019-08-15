package com.wj.service;

import com.wj.mapper.OrderStatusMapper;
import com.wj.pojo.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderStatusService")
public class OrderStatusServiceImpl implements IOrderStatusService{
    @Autowired
    private OrderStatusMapper orderStatusMapper;
    @Override
    public Integer addOrderStatus(OrderStatus orderStatus) {
        return orderStatusMapper.addOrderStatusMapper(orderStatus);
    }
}
