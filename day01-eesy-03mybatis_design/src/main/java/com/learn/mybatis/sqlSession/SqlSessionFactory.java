package com.learn.mybatis.sqlSession;

public interface SqlSessionFactory {
    /**
     * 打开一个新的SqlSession对象
     * @return
     */
    SqlSession openSession();
}
