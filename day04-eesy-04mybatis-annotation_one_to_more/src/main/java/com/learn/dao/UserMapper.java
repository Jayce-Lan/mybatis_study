package com.learn.dao;

import com.learn.pojo.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface UserMapper {

    /**
     * 多对一的字段查询
     *
     * 当数据库字段名与属性字段不一致时使用的注解
     * @Results 中设定id属性，定义往下都可以使用这个定义好的注解集
     * 余下语句可以使用@ ResultMap(value = {id})去调用这个属性
     *
     * @Result 中设置id = true声明主键
     * @return 返回查询结果
     */
    @Select("select * from user")
    @Results(id = "userMap", value = {
            @Result(id = true, property = "userId", column = "id"),
            @Result(property = "userName", column = "username"),
            @Result(property = "userSex", column = "sex"),
            @Result(property = "userAddress", column = "address"),
            @Result(property = "userBirthday", column = "birthday"),
            @Result(property = "accounts",
                    column = "id",
                    many = @Many(select = "com.learn.dao.AccountMapper.getAccountByUid", fetchType = FetchType.LAZY))
    })
    List<User> getUserList();

    @Select("select * from user where id = #{id}")
    @ResultMap(value = {"userMap"})
    User getUserById(Integer id);

    @Select("select * from user where username like #{username}")
    @ResultMap(value = {"userMap"})
    List<User> getUsersByName(String username);

}
