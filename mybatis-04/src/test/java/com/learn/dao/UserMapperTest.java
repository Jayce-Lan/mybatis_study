package com.learn.dao;

import com.learn.pojo.User;
import com.learn.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//log4j流程：pom.xml中导入jar文件》配置log4j配置文件》在mybatis-config.xml文件中引入》在测试类中使用

public class UserMapperTest {
    //org.apache.log4j.Logger;导入包，在这里可以使用这个lg4j提供的类来打印日志
    static Logger logger = Logger.getLogger(UserMapperTest.class);

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

    @Test
    public void getUserById() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

//        logger.info("进入成功");

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.getUserById(1);
        System.out.println(user);

        sqlSession.close();
    }

    @Test
    public void testLog4j() {
        System.out.println();
        logger.info("info: 进入log4j方法");
        logger.debug("debug:进入log4j方法");
        logger.error("error:进入log4j方法");
    }

    @Test
    public void getUserByLimit() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("startIndex", 0);
        map.put("pageSize", 2);

        List<User> userList = userMapper.getUserByLimit(map);

        for (User user : userList) {
            System.out.println(user);
        }

        sqlSession.close();
    }
}
