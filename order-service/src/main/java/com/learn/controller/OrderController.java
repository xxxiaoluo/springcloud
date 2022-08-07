package com.learn.controller;

import com.learn.pojo.Item;
import com.learn.pojo.Order;
import com.learn.pojo.User;
import com.learn.service.OrderService;
import com.web.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/{orderId}")
    public JsonResult<Order> getOrder(@PathVariable String orderId) {
        Order order = orderService.getOrder(orderId);
        return JsonResult.ok().data(order);
    }

    @GetMapping("/add")
    public JsonResult<?> addOrder() {
        Order order = new Order();
        order.setId("i7u6ygt45rf3e");
        order.setUser(new User(8,null,null));
        order.setItems(Arrays.asList(new Item[] {
                new Item(1,"商品1",1),
                new Item(2,"商品2",3),
                new Item(3,"商品3",1),
                new Item(4,"商品4",2),
                new Item(5,"商品5",5),
        }));
        orderService.addOrder(order);
        return JsonResult.ok().msg("添加订单成功");
    }

    @GetMapping("/favicon.ico")
    public void ico() {
    }
}
