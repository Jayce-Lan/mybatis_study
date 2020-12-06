package com.learn.dao;

import com.learn.domain.User;

import java.util.List;

public interface IUserDao {
    List<User> findAll();
}
