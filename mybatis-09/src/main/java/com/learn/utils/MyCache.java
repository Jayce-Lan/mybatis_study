package com.learn.utils;

import org.apache.ibatis.cache.Cache;

import java.util.concurrent.locks.ReadWriteLock;

//自定义一个缓存类，去实现Cache接口

public class MyCache implements Cache {
    public String getId() {
        return null;
    }

    public void putObject(Object key, Object value) {

    }

    public Object getObject(Object key) {
        return null;
    }

    public Object removeObject(Object key) {
        return null;
    }

    public void clear() {

    }

    public int getSize() {
        return 0;
    }

    public ReadWriteLock getReadWriteLock() {
        return null;
    }
}
