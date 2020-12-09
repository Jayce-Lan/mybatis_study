import com.learn.dao.UserMapper;
import com.learn.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MyTest {
    private InputStream inputStream;
    private SqlSession sqlSession;
    private UserMapper userMapper;

    @Before
    public void getSession() throws IOException {
        inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = factory.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @After
    public void destroy() throws IOException {
        sqlSession.close();
        inputStream.close();
    }

    @Test
    public void getUserById() {
        User user1 = userMapper.getUserById(1);
        System.out.println(user1);

        //清空缓存
        sqlSession.clearCache();

        User user2 = userMapper.getUserById(1);
        System.out.println(user2);
        System.out.println(user1 == user2);
    }

    /**
     * 用户的增删改以及clearCache()都会清空一级缓存
     */
    @Test
    public void testCache() {
        User user1 = userMapper.getUserById(1);
        System.out.println(user1);

        User testUser = new User();
        testUser.setAddress("南宁市江南区壮锦大道");
        testUser.setId(10);
        testUser.setSex("男");
        testUser.setBirthday(new Date());
        testUser.setUsername("李明");

        int count = userMapper.updateUser(testUser);
        sqlSession.commit();
        System.out.println(count == 1 ? "成功" : "失败");

        User user2 = userMapper.getUserById(1);
        System.out.println(user2);
        System.out.println(user1 == user2);
    }

    @Test
    public void getUserList() {
        List<User> userList = userMapper.getUserList();
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
