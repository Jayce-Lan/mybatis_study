import com.learn.dao.AccountMapper;
import com.learn.dao.UserMapper;
import com.learn.pojo.Account;
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
    private AccountMapper accountMapper;

    @Before
    public void getMapper() throws IOException {
        inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSession = new SqlSessionFactoryBuilder().build(inputStream).openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
        accountMapper = sqlSession.getMapper(AccountMapper.class);
    }

    @After
    public void closeSession() throws IOException {
        sqlSession.close();
        inputStream.close();
    }

    @Test
    public void findAllAccount() {
        List<Account> accountList = accountMapper.findAll();
        for (Account account : accountList) {
            System.out.println(account);
        }
    }

    @Test
    public void findAllUser() {
        List<User> userList = userMapper.findAll();
        for (User user : userList) {
            System.out.println(user);
            System.out.println(user.getAccounts());
        }
    }
}
