package com.learn.dao;

import com.learn.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    List<User> getUserList();
    User getUserById(int id);

    /**
     * 传入参数，进行分页查询数据库中的数据
     * @param map 至少传入一个参数
     * @return 返回一个或多个用户对象
     */
    List<User> getUserByLimit(Map<String, Integer> map);
}
