package com.learn.dao;

import com.learn.pojo.Account;

import java.util.List;

public interface AccountMapper {
    /**
     * 查询所有账户以及其用户信息
     * 方法2：通过Account实体类中创建一个User引用对象
     * @return 返回查询结果
     */
    List<Account> findAll();
}
