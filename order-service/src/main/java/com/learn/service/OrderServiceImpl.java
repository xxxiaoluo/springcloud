package com.learn.service;

import com.learn.feign.ItemClient;
import com.learn.feign.UserClient;
import com.learn.pojo.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ItemClient itemClient;
    @Autowired
    private UserClient userClient;

    @Override
    public Order getOrder(String orderId) {
        log.info("获取订单，orderId="+orderId);

        Order order = new Order();
        order.setId(orderId);
        return order;
    }

    @Override
    public void addOrder(Order order) {
        log.info("添加订单："+order);
        // 远程调用商品，减少库存
        itemClient.decreaseNumber(order.getItems());
        // 远程调用用户，增加积分
        userClient.addScore(order.getUser().getId(), 1000);
    }
}