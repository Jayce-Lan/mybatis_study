package com.learn.dao;

import com.learn.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    List<User> getUserList();
    User getUserById(int id);

    /**
     * 通过foreach查询用户
     * @param map 传入集合
     * @return 返回对应用户列表
     */
    List<User> queryUserForeach(Map map);
}
