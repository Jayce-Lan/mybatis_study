package com.learn.dao;

import com.learn.domain.QueryVo;
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
     * @return 返回用户数量
     */
    int findTotal();

    /**
     * 使用其他类调用传值的方式进行查询
     * @param queryVo 调用了User类的类
     * @return 返会查询结果
     */
    List<User> findUserByQueryVo(QueryVo queryVo);
}
