import com.learn.dao.UserMapper;
import com.learn.dao.impl.UserMapperImpl;
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
    private UserMapper userMapper;

    @Before
    public void getUserMapper() throws IOException {
        inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        userMapper = new UserMapperImpl(factory);
    }

    @After
    public void closeSqlSession() throws IOException {
        inputStream.close();
    }

    @Test
    public void findAll() {
        List<User> users = userMapper.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setId(2);
        user.setUserName("娜娜");
        user.setBirthday(new Date());
        user.setSex("女");
        user.setAddress("南宁市高新区高新大厦");
        int count = userMapper.updateUser(user);
        System.out.println(count == 1 ? "成功" : "失败");
    }
}
