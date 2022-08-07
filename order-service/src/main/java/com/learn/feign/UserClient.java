package com.learn.feign;

import com.learn.pojo.User;
import com.web.utils.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="user-service")
public interface UserClient {
    @GetMapping("/{userId}")
    JsonResult<User> getUser(@PathVariable Integer userId);

    @GetMapping("/{userId}/score")  //  /8/score?score=1000
    JsonResult<?> addScore(@PathVariable Integer userId, @RequestParam("score") Integer score);
}
