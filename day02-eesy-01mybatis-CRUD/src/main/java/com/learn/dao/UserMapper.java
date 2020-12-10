package com.learn.dao;

import com.learn.domain.QueryVo;
import com.learn.domain.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    List<User> findAll();
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(Integer id);
    User findById(Integer id);
    List<User> findByName(String username);

    /**
     * 通过map集合传入参数添加用户
     * @param map 传入用户信息
     * @return 成功返回1
     */
    int addUser(Map<String, Object> map);

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
