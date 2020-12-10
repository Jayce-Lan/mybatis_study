package com.learn.dao;

import com.learn.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    @Select("select * from user")
    List<User> getUserList();

    @Insert("insert into user (username, sex, address, birthday) values (#{username}, #{sex}, #{address}, #{birthday})")
    int addUser(Map<String, Object> map);

    @Update("update user " +
            "set username = #{username}, sex = #{sex}, birthday = #{birthday}, address = #{address} " +
            "where id = #{id}")
    int updateUser(User user);

    @Delete("delete from user where id = #{id}")
    int deleteUser(Integer id);

    @Select("select * from user where id = #{id}")
    User getUserById(Integer id);

    /**
     * 模糊查询查询用户
     * @param name 传入用户姓名存在的字段
     * @return 返回被查询结果
     */
    @Select("select * from user where username like #{username}")
//    @Select("select * from user where username like '%${value}%'")    //使用该方法容易出现sql注入
    List<User> getUserByName(String name);

    /**
     * 查询用户总数
     * @return 返回用户总数
     */
    @Select("select count(*) from user")
    int getUserCount();
}
