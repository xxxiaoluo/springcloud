package com.learn.service;

import com.learn.pojo.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService{

    @Override
    public List<Item> getItems(String orderId) {
        log.info("获取商品列表，orderId=" + orderId);

        //Demo数据
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(1,"商品1",1));
        items.add(new Item(2,"商品2",4));
        items.add(new Item(3,"商品3",1));
        items.add(new Item(4,"商品4",2));
        items.add(new Item(5,"商品5",6));
        return items;
    }

    @Override
    public void decreaseNumber(List<Item> items) {
        for(Item item : items){
            log.info("减少库存：" + item);
        }
    }
}
