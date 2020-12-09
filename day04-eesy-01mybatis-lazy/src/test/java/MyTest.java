import com.learn.dao.AccountMapper;
import com.learn.dao.UserMapper;
import com.learn.pojo.Account;
import com.learn.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyTest {
    private InputStream inputStream;
    private SqlSession sqlSession;
    private UserMapper userMapper;
    private AccountMapper accountMapper;

    @Before
    public void getMappers() throws IOException {
        inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSession = new SqlSessionFactoryBuilder().build(inputStream).openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
        accountMapper = sqlSession.getMapper(AccountMapper.class);
    }

    @Test
    public void getUserList() {
        List<User> userList = userMapper.getUserList();
        for (User user : userList) {
            System.out.println(user);
            System.out.println(user.getAccountList());
        }
    }

    @Test
    public void getUserById() {
        User user = userMapper.getUserById(1);
        System.out.println(user);
    }

    @Test
    public void getAccountList() {
        List<Account> accountList = accountMapper.getAccountList();
        for (Account account : accountList) {
            System.out.println(account);
//            System.out.println(account.getUser());
        }
    }
}
