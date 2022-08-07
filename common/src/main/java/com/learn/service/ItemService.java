package com.learn.service;

import com.learn.pojo.Item;

import java.util.List;

// 商品的业务接口
public interface ItemService {
    // 获取订单中的商品列表
    List<Item> getItems(String orderId);

    // 减少商品库存
    void decreaseNumber(List<Item> items);
}
