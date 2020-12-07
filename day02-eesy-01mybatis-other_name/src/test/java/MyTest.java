import com.learn.dao.UserMapper;
import com.learn.domain.User;
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
    public void getUserMapper() throws IOException {
        inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = factory.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @After
    public void closeSqlSession() throws IOException {
        sqlSession.close();
        inputStream.close();
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setUserId(1);
        user.setUserName("老王");
        user.setUserAddress("广西南宁市会展中心");
        user.setUserSex("男");
        user.setUserBirthday(new Date());
        int count = userMapper.updateUser(user);
        System.out.println(count == 1 ? "修改成功" : "修改失败");
        sqlSession.commit();
    }

    @Test
    public void findAll() {
        List<User> userList = userMapper.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
