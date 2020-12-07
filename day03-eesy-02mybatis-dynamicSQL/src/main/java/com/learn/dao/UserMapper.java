package com.learn.dao;

import com.learn.domain.QueryVo;
import com.learn.domain.User;

import java.util.List;

public interface UserMapper {
    List<User> findAll();
    User findById(Integer id);
    List<User> findByName(String username);


    /**
     * 使用其他类调用传值的方式进行查询
     * @param queryVo 调用了User类的类
     * @return 返会查询结果
     */
    List<User> findUserByQueryVo(QueryVo queryVo);

    /**
     * 通过条件查找用户
     * @param user 可能是用户属性的任意参数
     * @return 返回被查找的用户
     */
    List<User> findUserByCondition(User user);

    /**
     * 通过QueryVo提供的id集合查询用户信息
     * @param queryVo 传入参数（一个或多个id）
     * @return 返回查询结果
     */
    List<User> findUserByIds(QueryVo queryVo);
}
