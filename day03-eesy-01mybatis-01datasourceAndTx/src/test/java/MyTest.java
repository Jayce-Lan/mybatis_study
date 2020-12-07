import com.learn.dao.UserMapper;
import com.learn.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
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
    public void getMapper() throws IOException {
        inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        //openSession()如果传入true 那么事务将会自动提交，一般不使用
        sqlSession = new SqlSessionFactoryBuilder().build(inputStream).openSession(true);
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @After
    public void closeSession() throws IOException {
        sqlSession.close();
        inputStream.close();
    }

    @Test
    public void findAll() {
        List<User> userList = userMapper.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void saveUser() {
        User user = new User();
        user.setUsername("李明");
        user.setSex("男");
        user.setAddress("南宁市兴宁区中山路");
        user.setBirthday(new Date());
        //由于自动提交，因此不需要再手动提交
        userMapper.saveUser(user);
        System.out.println(userMapper.findById(user.getId()));
    }
}
