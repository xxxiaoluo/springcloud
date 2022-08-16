package com.learn.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.learn.pojo.User;
import com.web.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    // 注入 yml 中配置的 demo 数据
    @Value("${sp.user-service.users}")
    private String userJson;

    @Override
    public User getUser(Integer id) {
        log.info("获取用户， id="+id);
        // TypeReference 利用匿名内部类继承语法，写泛型类型：List<User>
        // userJson --> List<User>
        List<User> list = JsonUtil.from(userJson, new TypeReference<List<User>>() {});
        for (User u : list) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        // 不是7,8,9用户，返回一个写死的用户数据
        return new User(id, "用户名"+id, "密码"+id);
    }

    @Override
    public void addScore(Integer id, Integer score) {
        log.info("增加用户积分，id="+id+"， score="+score);
    }
}
