package com.learn.dao;

import com.learn.pojo.User;
import com.learn.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class UserDaoTest {
    /**
     * 用于测试dao层方法的测试用类
     */
    @Test
    public void test() {
        //第一步：获得SqlSession对象
        SqlSession sqlSession = null;

        //第二步：执行SQL

        try {
            sqlSession = MybatisUtils.getSqlSession();
            //方式一：获取mapper对象
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            //使用UserDao的方法返回List<User>对象
            List<User> userList = userDao.getUserList();
            
            //方式2（旧版）：
//            List<User> userList = sqlSession.selectList("com.learn.dao.UserDao.getUserList");
            for (User user : userList) {
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭sqlSession
            sqlSession.close();
        }
    }
}
