import com.learn.dao.UserMapper;
import com.learn.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class CacheTest {
    @Test
    public void testCache() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession1 = factory.openSession();

        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
        User user1 = userMapper1.getUserById(1);
        System.out.println(user1);
        sqlSession1.close();

        SqlSession sqlSession2 = factory.openSession();

        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        User user2 = userMapper2.getUserById(1);
        System.out.println(user2);
        sqlSession1.close();

        System.out.println(user1 == user2); //false 原因是二级缓存只负责存储数据而不是存储对象
    }
}
