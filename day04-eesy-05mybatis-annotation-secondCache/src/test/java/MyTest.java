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

public class MyTest {
    private InputStream inputStream;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private UserMapper userMapper;

    @Before
    public void getFactory() throws IOException {
        inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        factory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = factory.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @After
    public void destroy() throws IOException {
        sqlSession.close();
        inputStream.close();
    }

    @Test
    public void getUsers() {
        SqlSession sqlSession1 = factory.openSession();
        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);

        User user1 = userMapper1.getUserById(1);
        System.out.println(user1);

        sqlSession1.close();

        User user2 = userMapper.getUserById(2);
        System.out.println(user2);

        System.out.println(user1 == user2);
    }
}
