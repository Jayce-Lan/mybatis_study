package com.learn.dao.impl;

import com.learn.dao.IUserDao;
import com.learn.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class IUserDaoImpl implements IUserDao {
    private SqlSessionFactory factory = null;

    public IUserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    public List<User> findAll() {
        SqlSession sqlSession = factory.openSession();
        //这里的全类名对应的是mapper.xml文件中的namespace，findAll对应的是sql语句的id
        List<User> users = sqlSession.selectList("com.learn.dao.IUserDao.findAll");
        sqlSession.close();
        return users;
    }
}
