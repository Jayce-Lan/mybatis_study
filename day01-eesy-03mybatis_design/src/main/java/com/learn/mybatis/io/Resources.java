package com.learn.mybatis.io;

import java.io.InputStream;

public class Resources {
    /**
     * 根据传入参数获取字节输入流
     * @param filePath 传入配置文件名称
     * @return 返回字节输入流
     */
    public static InputStream getResourcesAsStream(String filePath) {
        return Resources.class.getClassLoader().getResourceAsStream(filePath);
    }
}
