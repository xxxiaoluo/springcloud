package com.learn.feign;

import com.learn.pojo.Item;
import com.web.utils.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/*
配置三条：
 - 调用哪个服务
 - 哪个路径
 - 向路径提交什么参数
 */
@FeignClient(name = "item-service")
public interface ItemClient {
    @GetMapping("/{orderId}")
    JsonResult<List<Item>> getItems(@PathVariable("orderId") String orderId);

    @PostMapping("/decreaseNumber")
    JsonResult<?> decreaseNumber(@RequestBody List<Item> items);
}
