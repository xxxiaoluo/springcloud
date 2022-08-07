package com.learn.controller;

import com.learn.pojo.Item;
import com.learn.service.ItemService;
import com.web.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@Slf4j
public class ItemController {
    @Autowired
    private ItemService itemService;

    /*  根据订单号获取商品信息  */
    @GetMapping("/{orderId}")
    public JsonResult getItems(@PathVariable String orderId) throws InterruptedException {
        List<Item> items = itemService.getItems(orderId);

        // 随机阻塞，90%概率执行阻塞代码
        if (Math.random() < 0.9) {
            // 暂停 [0, 5000) 毫秒
            int t = new Random().nextInt(50000);
            System.out.println("暂停："+t);
            Thread.sleep(t);
        }

        return JsonResult.ok().data(items);
    }

    /*  减少库存  */
    //@RequestBody 完整接收请求协议体数据
    @PostMapping("/decreaseNumber")
    public JsonResult<?> decreaseNumber(@RequestBody List<Item> items) {
        itemService.decreaseNumber(items);
        return JsonResult.ok().msg("减少库存成功");
    }
}
