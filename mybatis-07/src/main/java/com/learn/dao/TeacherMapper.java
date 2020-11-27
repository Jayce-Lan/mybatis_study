package com.learn.dao;

import com.learn.pojo.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherMapper {
   /**
    * 获取指定老师下的所有学生信息以及老师信息
    * @param id 传入老师的id
    * @return 返回该老师以及名下所有学生的信息
    */
   public Teacher getTeacher(@Param("id") int id);

   /**
    * 使用子查询方式查询
    * @param id 传入教师id
    * @return 返回对应的信息
    */
   public Teacher getTeacher2(@Param("id") int id);
}
