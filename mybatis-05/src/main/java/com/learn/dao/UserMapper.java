package com.learn.dao;

import com.learn.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    /**
     * 使用注解代替UserMapper.xml文件，将sql语句放在注解中
     * 但是复杂的语句不建议使用注解
     * @return  返回User列表
     */
    @Select("select * from user")
    List<User> getUserList();

    /**
     * 假如方法存在多个参数，需要添加@Param注解，里面的数值对应的是sql语句的查询参数
     * @param id 传入用户id
     * @param name 传入用户姓名
     * @return 查询返回一名用户
     */
    @Select("select * from user where id = #{id} and name = #{name}")
    User getUserByIdAndName(@Param("id") int id, @Param("name") String name);

    /**
     * 使用注解传入id查询用户
     * @param id 传入的id
     * @return 返回id对应的用户
     */
    @Select("select * from user where id = #{id}")
    User getUserById(@Param("id") int id);

    /**
     * 如果传入的参数为引用对象，那么不需要填写@Param
     * 但是要注意，这里传入的参数必须与引用对象类中的属性名一致
     * @param user 传入一个对象
     * @return 创建成功返回1
     */
    @Insert("insert into user(id, name, pwd) values(#{id}, #{name}, #{pwd})")
    int addUser(User user);

    @Update("update user set name = #{name}, pwd = #{pwd} where id = #{id}")
    int updateUser(User user);

    @Delete("delete from user where id = #{id}")
    int deleteUser(@Param("id") int id);
}
