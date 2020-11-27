import com.learn.dao.BlogMapper;
import com.learn.pojo.Blog;
import com.learn.utils.IDUtils;
import com.learn.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTest {
    @Test
    public void queryBlogIF() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        Map map = new HashMap();
        map.put("author", "jack");

        List<Blog> blogList = blogMapper.queryBlogIF(map);
        for (Blog blog : blogList) {
            System.out.println(blog);
        }

        sqlSession.close();
    }

    @Test
    public void queryBlogChoose() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        Map map = new HashMap();
        //由于choose语句有中断，因此查询出来即使存在浏览量低于500的，依旧会查询出来，因为传入了作者
        map.put("views", 500);
        map.put("author", "jayce");

        List<Blog> blogList = blogMapper.queryBlogChoose(map);

        for (Blog blog : blogList) {
            System.out.println(blog);
        }
    }

    @Test
    public void updataBlog() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

        Map map = new HashMap();
        map.put("id", "bcc1cf8679f74133aa0ab95e0c77b3f3");
        map.put("author", "Jack");
        int count = blogMapper.updataBlog(map);
        System.out.println(count == 1 ? "更新成功！" : "更新失败！");
        sqlSession.commit();

        sqlSession.close();
    }

    @Test
    public void addBlog() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

        Blog blog = new Blog();
        blog.setId(IDUtils.getId());
        blog.setTitle("Java教程");
        blog.setAuthor("Jayce");
        blog.setCreateTime(new Date());
        blog.setViews(99);
        //如果什么都不更新，会造成报错
        int count = blogMapper.addBlog(blog);
        System.out.println(count == 1 ? "插入成功" : "插入失败");
        sqlSession.commit();

        /*blog.setId(IDUtils.getId());
        blog.setTitle("Spring教程");
        count = blogMapper.addBlog(blog);
        System.out.println(count == 1 ? "插入成功" : "插入失败");
        sqlSession.commit();

        blog.setId(IDUtils.getId());
        blog.setTitle("微服务教程");
        count = blogMapper.addBlog(blog);
        System.out.println(count == 1 ? "插入成功" : "插入失败");
        sqlSession.commit();*/

        sqlSession.close();
    }
}
