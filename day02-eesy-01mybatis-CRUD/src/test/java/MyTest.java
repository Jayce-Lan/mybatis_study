import com.learn.dao.UserMapper;
import com.learn.domain.QueryVo;
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

    @Before //用于在测试方法执行之前执行
    public void init() throws IOException {
        inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = factory.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    /**
     * 释放资源
     */
    @After  //用于在测试方法执行之后执行
    public void destroy() throws IOException {
        sqlSession.close();
        inputStream.close();
    }

    @Test
    public void findALl() {
        List<User> users = userMapper.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void saveUser() {
        /*User user = new User();
        user.setUsername("莉莉");
        user.setAddress("南宁市兴宁区新民路");
        user.setSex("女");
        user.setBirthday(new Date());*/

        User user = new User("老李", "南宁市西乡塘区广西民族大学", "女", new Date());
        System.out.println("执行保存操作前" + user);
        userMapper.saveUser(user);
        sqlSession.commit();    //如果不提交会造成事务回滚
        System.out.println("执行保存操作后" + user);   //由于xml文件中插入了保存后获取id的语句，因此保存后会得到用户id
        System.out.println(userMapper.findById(user.getId()));
    }

    @Test
    public void updateUser() {
        User user = new User(6,"老刘", "广州市番禺区华侨大学", "女", new Date());
        userMapper.updateUser(user);
        sqlSession.commit();
    }

    @Test
    public void deleteUser(){
        userMapper.deleteUser(6);
        sqlSession.commit();
    }

    @Test
    public void findById() {
        User user = userMapper.findById(2);
        System.out.println(user);
    }

    @Test
    public void findByName() {
        String username = "老";
        username = "%" + username + "%";
        List<User> users = userMapper.findByName(username);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void findTotal() {
        int count = userMapper.findTotal();
        System.out.println(count);
    }

    @Test
    public void findUserByQueryVo() {
        QueryVo queryVo = new QueryVo();
        User user = new User();
        String uname = "老";
        uname = "%" + uname + "%";
        user.setUsername(uname);
        queryVo.setUser(user);
        List<User> users = userMapper.findUserByQueryVo(queryVo);

        for (User user1 : users) {
            System.out.println(user1);
        }
    }
}
