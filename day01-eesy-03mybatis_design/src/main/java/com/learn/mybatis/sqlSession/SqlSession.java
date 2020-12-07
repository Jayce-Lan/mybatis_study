package com.learn.mybatis.sqlSession;

//自定义和数据库交互的核心类，可以创建dao接口的代理对象
public interface SqlSession {
    /**
     * 根据参数创建代理对象
     * @param daoInterfaceClass dao的接口字节码
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<T> daoInterfaceClass);

    /**
     * 释放资源
     */
    void close();
}
