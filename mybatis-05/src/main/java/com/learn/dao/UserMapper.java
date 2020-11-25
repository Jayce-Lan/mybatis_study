package com.learn.dao;

import com.learn.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    /**
     * 使用注解代替UserMapper.xml文件，将sql语句放在注解中
     * 但是复杂的语句不建议使用注解
     * @return  返回User列表
     */
    @Select("select * from user")
    List<User> getUserList();
}
