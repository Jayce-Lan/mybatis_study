import com.learn.dao.StudentMapper;
import com.learn.dao.TeacherMapper;
import com.learn.pojo.Student;
import com.learn.pojo.Teacher;
import com.learn.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MyTest {
    /**
     * 用于测试数据库是否连接成功的用类
     */
    @Test
    public void testDemo() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
        Teacher teacher = teacherMapper.getTeacherById(1);
        System.out.println(teacher);
        sqlSession.close();
    }

    @Test
    public void getStudentMsg() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> studentList = studentMapper.getStudentMsg();

        for (Student student : studentList) {
            System.out.println(student);
        }

        sqlSession.close();
    }
}
