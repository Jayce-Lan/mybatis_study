import com.learn.dao.IUserDao;
import com.learn.dao.impl.IUserDaoImpl;
import com.learn.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyTest {
    @Test
    public void findAll() throws IOException {
        //读取配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        //创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        //使用工厂产生SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //使用SqlSession创建Dao接口的代理对象
        IUserDao iUserDao = sqlSession.getMapper(IUserDao.class);
        //使用代理对象执行方法
        List<User> users = iUserDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        //释放资源
        sqlSession.close();
        inputStream.close();
    }

    /**
     * 使用dao的实现类去实现查询方法
     */
    @Test
    public void findAllByImpl() throws IOException {
        //读取配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        //创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        IUserDao iUserDao = new IUserDaoImpl(factory);
        List<User> users = iUserDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        inputStream.close();
    }
}
