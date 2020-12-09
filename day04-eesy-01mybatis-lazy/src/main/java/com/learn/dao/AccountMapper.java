package com.learn.dao;

import com.learn.pojo.Account;

import java.util.List;

public interface AccountMapper {
    List<Account> getAccountList();

    List<Account> getAccountByUserId();
}
