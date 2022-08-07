package com.learn.controller;
import com.learn.pojo.User;
import com.learn.service.UserService;
import com.web.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public JsonResult<User> getUser(@PathVariable Integer userId) {
        User user = userService.getUser(userId);
        return JsonResult.ok().data(user);
    }

    //  http://localhost:8101/8/score?score=1000
    @GetMapping("/{userId}/score")
    public JsonResult<?> addScore(@PathVariable Integer userId,
                                  Integer score) {
        userService.addScore(userId, score);
        return JsonResult.ok().msg("增加积分成功");
    }

    @GetMapping("/favicon.ico")
    public void ico() {
    }
}
