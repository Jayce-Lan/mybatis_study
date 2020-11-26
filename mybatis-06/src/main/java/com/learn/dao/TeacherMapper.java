package com.learn.dao;

import com.learn.pojo.Teacher;
import org.apache.ibatis.annotations.Param;

public interface TeacherMapper {

   Teacher getTeacherById(@Param("id") int id);
}
