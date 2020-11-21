package com.learn.dao;

import com.learn.pojo.User;
import com.learn.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMapperTest {

    @Test
    public void getUserLike() {
        SqlSession sqlSession = null;
        sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        String value = "李";
        List<User> userList = userMapper.getUserLike("%" + value + "%");

        for (User user : userList) {
            System.out.println(user);
        }

        sqlSession.close();
    }

    /**
     * 用于测试dao层方法的测试用类
     * 测试数据库表的查询语句
     */
    @Test
    public void getUserList() {
        //第一步：获得SqlSession对象
        SqlSession sqlSession = null;

        //第二步：执行SQL

        try {
            sqlSession = MybatisUtils.getSqlSession();
            //方式一：获取mapper对象
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //使用UserDao的方法返回List<User>对象
            List<User> userList = userMapper.getUserList();
            
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

    /**
     * 测试按照id查找用户的方法
     */
    @Test
    public void getUserById() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.getUserById(1);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 增加用户的测试类
     * 增删改需要提交事务
     */
    @Test
    public void addUser() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            int count = userMapper.addUser(new User(4, "小明", "666999"));
            if (count > 0)
                System.out.println("添加成功！");
            //提交事务
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 使用Map集合插入对象
     */
    @Test
    public void addUser2() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", 5);
            map.put("userName", "李六");
            map.put("passWord", "000999");
            int count = userMapper.addUser2(map);
            System.out.println(count > 0 ? "添加成功！" : "添加失败！");
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 用于测试用户修改信息的语句
     */
    @Test
    public void updateUser() {
        SqlSession sqlSession = null;

        try {
            sqlSession = MybatisUtils.getSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            int count = userMapper.updateUser(new User(1, "张强", "777888"));
            System.out.println(count > 0 ? "修改成功！" : "修改失败！");
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 测试删除用户的类
     */
    @Test
    public void deleteUser() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            int count = userMapper.deleteUser(4);
            System.out.println(count > 0 ? "删除成功！" : "删除失败！");
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }
}
