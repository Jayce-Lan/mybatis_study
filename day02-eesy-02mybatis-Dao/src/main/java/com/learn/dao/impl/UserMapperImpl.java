package com.learn.dao.impl;

import com.learn.dao.UserMapper;
import com.learn.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserMapperImpl implements UserMapper {

    private SqlSessionFactory factory;

    public UserMapperImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    public List<User> findAll() {
        SqlSession sqlSession = factory.openSession();
        List<User> users = sqlSession.selectList("com.learn.dao.UserMapper.findAll");
        sqlSession.close();
        return users;
    }

    public int updateUser(User user) {
        SqlSession sqlSession = factory.openSession();
        int count = sqlSession.insert("com.learn.dao.UserMapper.updateUser", user);
        sqlSession.commit();
        return count;
    }
}
