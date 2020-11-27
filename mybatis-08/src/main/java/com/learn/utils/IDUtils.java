package com.learn.utils;

import java.util.UUID;

//创建一个随机生成id的类

public class IDUtils {
    /**
     * 随机获取并创建用户id
     * @return 返回一个随机生成的用户id
     */
    public static String getId(){
        //@replaceAll 把随机生成的id中的"-"替换成空值
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void main(String[] args) {
        System.out.println(IDUtils.getId());
    }
}
