import com.learn.dao.RoleMapper;
import com.learn.dao.UserMapper;
import com.learn.pojo.Role;
import com.learn.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyTest {
    private InputStream inputStream;
    private SqlSession sqlSession;
    private UserMapper userMapper;
    private RoleMapper roleMapper;

    @Before
    public void getMapper() throws IOException {
        inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSession = new SqlSessionFactoryBuilder()
                .build(inputStream)
                .openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
        roleMapper = sqlSession.getMapper(RoleMapper.class);
    }

    @After
    public void closeSession() throws IOException {
        sqlSession.close();
        inputStream.close();
    }

    @Test
    public void getUserList() {
        List<User> userList = userMapper.getUserList();
        for (User user : userList) {
            System.out.println(user);
            System.out.println(user.getRoleList());
        }
    }

    @Test
    public void getRoleList() {
        List<Role> roleList = roleMapper.getRoleList();
        for (Role role : roleList) {
            System.out.println(role);
            System.out.println(role.getUserList());
        }
    }
}
