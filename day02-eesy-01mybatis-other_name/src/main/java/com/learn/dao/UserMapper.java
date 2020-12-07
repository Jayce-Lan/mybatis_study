package com.learn.dao;

import com.learn.domain.User;

import java.util.List;

public interface UserMapper {
    List<User> findAll();
    int updateUser(User user);
}
