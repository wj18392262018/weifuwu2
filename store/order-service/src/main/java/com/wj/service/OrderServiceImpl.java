package com.wj.service;

import com.wj.mapper.OrderMapper;
import com.wj.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderService")
public class OrderServiceImpl implements IOrderService{
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public Integer addOrder(Order order) {
        return orderMapper.addOrder(order);
    }
}
