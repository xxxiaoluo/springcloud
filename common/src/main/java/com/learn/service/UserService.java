package com.learn.service;

import com.learn.pojo.User;

public interface UserService {
    // 获取用户
    User getUser(Integer id);

    // 增加积分
    void addScore(Integer id, Integer score);
}
