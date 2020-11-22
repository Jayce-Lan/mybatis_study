package com.learn.dao;

import com.learn.pojo.User;
import com.learn.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class UserMapperTest {
    @Test
    public void getUserLits() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
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
        User user = userMapper.getUserById(1);

        System.out.println(user);

        sqlSession.close();
    }
}
