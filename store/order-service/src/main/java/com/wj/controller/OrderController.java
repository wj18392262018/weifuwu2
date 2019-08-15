package com.wj.controller;

import com.google.gson.Gson;
import com.wj.pojo.Order;
import com.wj.pojo.OrderDetail;
import com.wj.pojo.OrderStatus;
import com.wj.pojo.Sku;
import com.wj.service.IOrderDetailService;
import com.wj.service.IOrderService;
import com.wj.service.IOrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private SnowFlake snowFlake;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IOrderDetailService orderDetailService;
    @Autowired
    private IOrderStatusService orderStatusService;
    @RequestMapping("/submitOrder")
    public String submitOrder(Long zong, String zifu, String userId,String skus){
        //获取雪花id
        long id=snowFlake.nextId();
        Order order=new Order();
        order.setOrderId(id);
        order.setTotalPay(zong);
        order.setActualPay(zong);
        order.setPaymentType(Integer.parseInt(zifu));
        order.setCreateTime(new Date());
        order.setUserId(userId);
        Gson gson=new Gson();
        Sku[] sku=gson.fromJson(skus,Sku[].class);
        orderService.addOrder(order);
        for (int i = 0; i <sku.length ; i++) {
            OrderDetail od=new OrderDetail(id,sku[i].getId(),sku[i].getNum(),sku[i].getTitle(),sku[i].getPrice());
            orderDetailService.addOrderDetail(od);
        }
        OrderStatus orderStatus=new OrderStatus(id,Integer.parseInt(zifu),new Date());
        orderStatusService.addOrderStatus(orderStatus);
        return id+"";
    }
}
