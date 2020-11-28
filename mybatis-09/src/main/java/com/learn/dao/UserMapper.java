package com.learn.dao;

import com.learn.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> getUserList();
    User getUserById(@Param("id") int id);
    int updateUser(User user);
}
