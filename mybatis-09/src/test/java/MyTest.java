import com.learn.dao.UserMapper;
import com.learn.pojo.User;
import com.learn.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MyTest {
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
