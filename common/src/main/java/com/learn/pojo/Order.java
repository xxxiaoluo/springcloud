package com.learn.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    /**
     * 订单
     */

    private String id;      // 20210812USER74567845YUGHJ9485Y
    private User user;          // 用户
    private List<Item> items;   // 订单的商品列表
}