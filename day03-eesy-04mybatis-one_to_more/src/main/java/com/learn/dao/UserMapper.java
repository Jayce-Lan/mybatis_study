package com.learn.dao;

import com.learn.pojo.User;

import java.util.List;

public interface UserMapper {
    List<User> findAll();
    User findUserById(int id);
}
