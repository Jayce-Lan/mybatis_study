package com.learn.dao;

import com.learn.pojo.Blog;
import java.util.List;
import java.util.Map;

public interface BlogMapper {
    /**
     * 通过动态sql中的if查询博客
     * @param map 传入一个map参数
     * @return 返回被查询的值
     */
    List<Blog> queryBlogIF(Map map);

    /**
     * 通过动态sql中的choose语句查询博客
     * @param map 传入一个集合
     * @return 返回被查询的值
     */
    List<Blog> queryBlogChoose(Map map);

    /**
     * 使用set属性更新博客
     * @param map 传入一个集合
     * @return 返回值为1则更新成功
     */
    int updataBlog(Map map);

    /**
     * 向数据库中插入数据
     * @param blog 插入实体类Blog
     * @return 插入成功返回1
     */
    int addBlog(Blog blog);
}
