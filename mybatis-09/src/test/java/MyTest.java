import com.learn.dao.UserMapper;
import com.learn.pojo.User;
import com.learn.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MyTest {
    /**
     * 用于测试二级缓存的方法
     */
    @Test
    public void testCache() {
        SqlSession sqlSession1 = MybatisUtils.getSqlSession();
        SqlSession sqlSession2 = MybatisUtils.getSqlSession();

        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
        User user1 = userMapper1.getUserById(1);
        System.out.println(user1);

        sqlSession1.close();//二级缓存在一级缓存结束后开启，因此需要先关闭一级缓存

        System.out.println("=============================================");

        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        User user2 = userMapper2.getUserById(1);
        User user3 = userMapper2.getUserById(2);    //如果查询到的是新的用户，那么会再次查询，因为缓存中没有数据
        System.out.println(user2);
        System.out.println(user3);

        sqlSession2.close();
    }

    /**
     * 用于测试一级缓存的方法
     */
    @Test
    public void getUserById() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user1 = userMapper.getUserById(1);
        System.out.println(user1);

//        userMapper.updateUser(new User(2, "李雷", "111000"));
//        sqlSession.commit();

        //清理缓存
        sqlSession.clearCache();
        System.out.println("=======================================");

        User user2 = userMapper.getUserById(1);
        System.out.println(user2);
    }

    @Test
    public void getUserList() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.getUserList();

        for (User user : userList) {
            System.out.println(user);
        }

        System.out.println("====================================");

        List<User> userList2 = userMapper.getUserList();

        for (User user : userList2) {
            System.out.println(user);
        }

        sqlSession.close();
    }
}
