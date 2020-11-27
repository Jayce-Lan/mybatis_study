# mybatis_project



## 入门案例mybatis-01

### 启动流程

- 使用idea搭建maven项目

- 配置`pom.xml`文件，注入`mysql`/`mybatis`/`junit`的依赖

  - ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <project xmlns="http://maven.apache.org/POM/4.0.0"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
        <modelVersion>4.0.0</modelVersion>
    
        <!--父工程-->
        <groupId>org.example</groupId>
        <artifactId>mybatis_study</artifactId>
        <version>1.0-SNAPSHOT</version>
    
        <!--导入依赖-->
        <dependencies>
            <!--mysql驱动-->
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>8.0.21</version>
                <scope>runtime</scope>
            </dependency>
            <!--mybatis-->
            <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
            <dependency>
                <groupId>org.mybatis</groupId>
                <artifactId>mybatis</artifactId>
                <version>3.5.2</version>
            </dependency>
            <!--junit-->
            <dependency>
                <groupId>junit</groupId>
                <artifactId>junit</artifactId>
                <version>4.12</version>
                <scope>test</scope>
            </dependency>
        </dependencies>
    
    </project>
    ```



### 创建项目顺序

- 创建项目`mybatis-01`
- 配置`pom.xml`文件
- 配置`mybatis-config.xml`文件
- 写入`MybatisUtiles.java`工具类
- 创建一个`User.java`类实体化对象
- 创建一个`UserMapper.java`(UserDao)实例化接口
- 配置`UserMapper.xml`写入SQL语句并实现UserDao中的方法
- 创建`UserDaoTest.java`测试用例



### Mybatis执行流程

- `Resources`获取加载全局配置文件
- 实例化`SqlSessionFactoryBuilder`构造器
- 解析配置文件流`XML ConfigBuilder`
- `Configuration`所有的配置信息
- `sqlSessionFactory`实例化
- `transactional`事务管理
- 创建`executor`执行器
- 创建`sqlSession`实现CRUD（实现失败事务会回滚到事务管理当中）
- 查看是否执行成功（查看失败事务会回滚到事务管理当中）
- 提交事务
- 关闭



### 项目目录

- mybatis_study(父类项目文件夹)
  - mybatis-01：子类项目文件夹
    - src
      - main
        - java：用于存放Java代码的地方
          - com.learn
            - dao：存放接口的文件夹
              - `UserMapper`：用于操作数据库实例的接口
              - `UserMapper.xml`：用于存放SQL语句的配置文件，其每个数据库语句的id都对应所需实现`UserMapper`的方法，类似于原本的`UserDaoImpl`
            - pojo：存放实现类的文件夹
              - `User`：映射数据库中的对象
            - utils：存放工具类的文件夹
              - `MybatisUtils`：用于获取sqlSessionFactory对象的工厂类
          - resources：存放配置文件的文件夹
            - `mybatis-config.xml`：里面存放了JDBC的参数
        - test：用于存放测试类代码
          - java：将对应的需要测试的Dao文件夹名称放入此处
            - com.learn
              - dao
                - `UserDaoTest`：测试UserDao的测试类
    - `pom.xml`：子级maven依赖，继承父级
  - `pom.xml`：父级maven依赖



### 注意事项

#### 测试编译中缺少xml实例而编译失败

> 在`pom.xml`中需要配置以下文件，用以防止在测试编译中dao包中创建的xml文件不被编译的情况发生

```xml
<!--在build中配置resources，来防止我们资源到处失败问题-->
<build>
    <resources>
        <resource>
            <directory>src/main/resources</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>true</filtering>
        </resource>
        <resource>
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>true</filtering>
        </resource>
    </resources>
</build>
```



#### 配置文件问题

> 在resources文件夹中，要注意在<mappers></mappers>中注册好dao层的所有xml文件，否则会编译失败

```xml
<mappers>
    <mapper resource="com/learn/dao/UserMapper.xml"/>
</mappers>
```



## CRUD

> `UserMapper.xml`中的`namespace`包名必须和Dao接口包名一致
>
> 值得注意的是，增删改需要提交事务

### `xxxMapper.xml`文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--命名空间namespace绑定一个指定的Dao/Mapper接口-->
<mapper namespace="com.learn.dao.UserMapper">
    <!--
        @id 对应UserMapper的方法名
        @resultType sql语句查询的返回值
        @resultMap 返回查询集合
		@parameterType 查询语句所需的参数的类型
    -->
    <select id="getUserList" resultType="com.learn.pojo.User" parameterType="">
        select * from mybatis.user;
    </select>
</mapper>
```



#### select

> 选择、查询语句

```xml
<!--
        @id 对应UserMapper的方法名
        @resultType sql语句查询的返回值
        @resultMap 返回查询集合
    -->
<select id="getUserList" resultType="com.learn.pojo.User">
    select * from mybatis.user;
</select>

<!--
        @id 对应UserMapper的方法名
        @resultType sql语句查询的返回值
        @parameterType 查询语句所需的参数的类型
    -->
<select id="getUserById" resultType="com.learn.pojo.User" parameterType="int">
    select * from  user where id = #{id}
</select>
```



#### insert

> 插入、新增语句

```xml
<!--对象中的属性可以直接取出，这里没有返回值-->
<insert id="addUser" parameterType="com.learn.pojo.User">
    insert into user (id, name, pwd) values (#{id}, #{name}, #{pwd});
</insert>
```



#### update

> 修改语句

```xml
<!--修改用户-->
<update id="updateUser" parameterType="com.learn.pojo.User">
    update user set name = #{name}, pwd = #{pwd} where id = #{id};
</update>
```



#### delete

> 删除语句

```xml
<!--删除用户-->
<delete id="deleteUser" parameterType="int">
    delete from user where  id = #{id};
</delete>
```



## Map实现模糊查改

> 使用Map集合插入语句可以使得在UserMapper.xml中的sql语句插入字符不再受到User这个类的限制
>
> 假设数据表中的字段过多，建议使用Map

```java
 @MyTest
    public void addUser2() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", 4);
            map.put("userName", "李雷");
            map.put("passWord", "000999");
            int count = userMapper.addUser2(map);
            System.out.println(count > 0 ? "添加成功！" : "添加失败！");
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }
```

sql语句中的字段只需要和Map集合的键相同即可

```xml
<!--使用map集合新增数据库语句-->
<insert id="addUser2" parameterType="map">
    <!--所需插入数据和Map的键名是相同的-->
    insert into user (id, name, pwd) values (#{userId}, #{userName}, #{passWord});
</insert>
```

#### 注意的点

- Map传递参数，直接在sql中取出key即可【`parameterType="map"`】
- 对象传递参数，直接在sql语句中填写对象的属性即可【`parameterType="Object"`】
- 只有一个基本类型参数的情况下，可以直接在sql中取到【`parameterType="int"`，或者留空不写】
- 多个参数建议使用Map或注解



## 模糊查询

> Java代码运行时执行通配符，不要将模糊查询写死在xml中，否则会容易造成SQL注入

```java
@MyTest
public void getUserLike() {
    SqlSession sqlSession = null;
    sqlSession = MybatisUtils.getSqlSession();
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
    String value = "李";
    List<User> userList = userMapper.getUserLike("%" + value + "%");

    for (User user : userList) {
        System.out.println(user);
    }

    sqlSession.close();
}
```

在Java中拼接好了通配符，那么在xml中只需要传入稳定的字符串就可以了

```xml
<!--模糊查询用户信息-->
<select id="getUserLike" resultType="com.learn.pojo.User" parameterType="map">
    select * from user where name like #{value};
</select>
```

