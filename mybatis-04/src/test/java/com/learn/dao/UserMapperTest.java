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
        UserMapper userMpper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMpper.getUserList();

        for (User user : userList) {
            System.out.println(user);
        }

        sqlSession.close();
    }
}
