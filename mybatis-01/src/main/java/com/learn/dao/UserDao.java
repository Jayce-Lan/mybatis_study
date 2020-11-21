package com.learn.dao;

//用于操作数据库实例的接口

import com.learn.pojo.User;

import java.util.List;

public interface UserDao {
    /**
     * 获取实体类对象
     * @return
     */
    List<User> getUserList();
}
