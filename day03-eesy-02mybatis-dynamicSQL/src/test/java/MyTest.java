import com.learn.dao.UserMapper;
import com.learn.domain.QueryVo;
import com.learn.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MyTest {
    private InputStream inputStream;
    private SqlSession sqlSession;
    private UserMapper userMapper;

    @Before
    public void getMapper() throws IOException {
        inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSession = new SqlSessionFactoryBuilder().build(inputStream).openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @After
    public void closeSession() throws IOException {
        sqlSession.close();
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
    public void findByCondition() {
        User user = new User();
        user.setUsername("老王");
        user.setSex("女");
        List<User> users = userMapper.findUserByCondition(user);
        for (User user1 : users) {
            System.out.println(user1);
        }
    }

    @Test
    public void findUserByIds() {
        QueryVo queryVo = new QueryVo();
        List<Integer> integerList = new ArrayList<Integer>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        queryVo.setIds(integerList);
        List<User> userList = userMapper.findUserByIds(queryVo);

        for (User user : userList) {
            System.out.println(user);
        }
    }
}
