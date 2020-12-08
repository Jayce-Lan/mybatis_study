package com.learn.dao;

import com.learn.pojo.Account;
import com.learn.pojo.AccountUser;

import java.util.List;

public interface AccountMapper {
    /**
     * 获取所有账户，同时获取当前账户所属用户信息
     * @return 返回查询结果
     */
    List<Account> findAll();

    /**
     * 查询所有账户以及其用户信息
     * 方法1：通过新创建一个继承Account的实体类实现（不常用）
     * @return
     */
    List<AccountUser> findAllAccount();
}
