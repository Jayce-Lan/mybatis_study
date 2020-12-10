import com.learn.dao.UserMapper;
import com.learn.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTest {
    private InputStream inputStream;
    private SqlSession sqlSession;
    private UserMapper userMapper;

    @Before
    public void getSession() {
        try {
            inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            sqlSession = new SqlSessionFactoryBuilder().build(inputStream).openSession();
            userMapper = sqlSession.getMapper(UserMapper.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void destroy() {
        try {
            sqlSession.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUserList() {
        List<User> userList = userMapper.getUserList();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void addUser() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", "李明");
        map.put("sex", "男");
        map.put("address", "广西南宁市西乡塘区广西财经学院");
        map.put("birthday", new Date(95, 10, 30, 11, 22, 23));
        int count = userMapper.addUser(map);
        System.out.println(count == 1 ? "成功" : "失败");
        sqlSession.commit();
    }

    @Test
    public void updateUser() {
        int count =
                userMapper.updateUser(new User(10, "李晓明", "男", "南宁市江南区壮锦大道", new Date()));
        System.out.println(count == 1 ? "成功" : "失败");
        sqlSession.commit();
    }

    @Test
    public void deleteUser() {
        int count = userMapper.deleteUser(14);
        System.out.println(count == 1 ? "成功" : "失败");
        sqlSession.commit();
    }

    @Test
    public void getUserById() {
        User user = userMapper.getUserById(14);
        System.out.println(user);
    }
}
