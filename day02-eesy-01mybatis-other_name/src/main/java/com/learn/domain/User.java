package com.learn.domain;

import java.util.Date;

public class User {
    private Integer userId;
    private String userName;
    private String userSex;
    private String userAddress;
    private Date userBirthday;

    public User() {
    }

    public User(Integer userId, String userName, String userSex, String userAddress, Date userBirthday) {
        this.userId = userId;
        this.userName = userName;
        this.userSex = userSex;
        this.userAddress = userAddress;
        this.userBirthday = userBirthday;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userBirthday=" + userBirthday +
                '}';
    }
}
