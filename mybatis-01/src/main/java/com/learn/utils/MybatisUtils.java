package com.learn.utils;

//用于获取sqlSessionFactory对象的工厂类

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtils {
    private static SqlSessionFactory sqlSessionFactory;
    //静态加载，获取sqlSessionFactory对象，使得该资源一开始就被加载进来
    static {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取一个SqlSession类，并且再这里操作数据库
     * @return sqlSessionFactory.openSession();返回的是一个SqlSession类，该类可以调用数据库语句的方法
     */
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
