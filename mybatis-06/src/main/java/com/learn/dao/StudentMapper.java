package com.learn.dao;

import com.learn.pojo.Student;

import java.util.List;

public interface StudentMapper {
    /**
     * 查询所有学生信息，以及对应的老师
     * @return 返回信息
     */
    List<Student> getStudentMsg();
}
