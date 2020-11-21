package com.learn.dao;

//用于操作数据库实例的接口

import com.learn.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    /**
     * 模糊查询用户
     * @return 返回符合条件的用户
     */
    List<User> getUserLike(String value);

    /**
     * 获取实体类对象(全部用户)
     * @return
     */
    List<User> getUserList();

    /**
     * 根据id查询用户
     * @param id 传入一个用户的id
     * @return 返回id对应的用户
     */
    User getUserById(int id);

    /**
     * 插入一个用户
     * @param user 插入用户的信息
     * @return 插入成功返回1
     */
    int addUser(User user);

    /**
     * 使用Map集合插入用户
     * @param map 传入的集合
     * @return 插入成功返回1
     */
    int addUser2(Map<String, Object> map);

    /**
     * 修改用户
     * @param user 被修改的用户
     * @return 修改成功返回1
     */
    int updateUser(User user);

    /**
     * 删除用户
     * @param id
     * @return
     */
    int deleteUser(int id);
}
