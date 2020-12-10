package com.learn.dao;

import com.learn.pojo.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface AccountMapper {
    /**
     * 查询所有账户并获取到账户下的用户信息
     *
     *  @Result (property = "user",     对应的属性名
     *           column = "uid",        对应的数据库字段名
     *           one = @One(select = "com.learn.dao.UserMapper.getUserById", 对应的全限定类名+方法
     *                    fetchType = FetchType.EAGER)) 加载的模式（懒加载和立即加载）
     *
     * @return 用户信息
     */
    @Select("select * from account")
    @Results(id = "accountMap", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "money", column = "money"),
            @Result(property = "user",
                    column = "uid",
                    one = @One(select = "com.learn.dao.UserMapper.getUserById",
                            fetchType = FetchType.EAGER))
    })
    List<Account> getAccountList();

    @Select("select * from account where uid = #{uid}")
    List<Account> getAccountByUid(Integer uid);
}
