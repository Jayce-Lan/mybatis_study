package com.learn.dao;

import com.learn.pojo.User;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//@CacheNamespace 中的blocking默认为false，改成true之后会开启二级缓存
@CacheNamespace(blocking = true)
public interface UserMapper {
    @Select("select * from user")
    List<User> getUsers();

    @Select("select * from user where id = #{id}")
    User getUserById(Integer id);
}
