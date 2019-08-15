package com.wj.service;

import com.wj.mapper.OrderDetailMapper;
import com.wj.pojo.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderDetailService")
public class OrderDetailServiceImpl implements IOrderDetailService{
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Override
    public Integer addOrderDetail(OrderDetail orderDetail) {
        return orderDetailMapper.addOrderDetail(orderDetail);
    }
}
