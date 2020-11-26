package com.learn.dao;

import com.learn.pojo.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeacherMapper {

   Teacher getTeacherById(@Param("id") int id);
}
