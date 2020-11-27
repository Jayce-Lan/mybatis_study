package com.learn.dao;

import com.learn.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    List<User> getUserList();
    List<User> queryUserForeach(Map map);
}
