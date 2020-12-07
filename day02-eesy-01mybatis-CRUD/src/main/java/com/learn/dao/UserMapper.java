package com.learn.dao;

import com.learn.domain.User;

import java.util.List;

public interface UserMapper {
    List<User> findAll();
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(Integer id);
    User findById(Integer id);
    List<User> findByName(String username);

    /**
     * 查询总用户数
     * @return
     */
    int findTotal();
}
