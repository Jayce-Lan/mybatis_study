package com.learn.dao;

import com.learn.pojo.User;
import com.learn.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class UserMapperTest {
    @Test
    public void getUserList() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //底层主要应用反射去实现
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.getUserList();

        for (User user : userList) {
            System.out.println(user);
        }

        sqlSession.close();
    }

    @Test
    public void getUserById() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //由于在MybatisUtils中已经设定了自动提交，因此不再需要提交事务
        User user = userMapper.getUserById(1);
        System.out.println(user);
        sqlSession.close();
    }

    @Test
    public void addUser() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int count = userMapper.addUser(new User(6, "张强", "999888"));
        System.out.println(count == 1 ? "添加成功" : "添加失败！");
        sqlSession.close();
    }

}
