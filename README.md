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

> 也可以使用class进行注册

```xml
<mappers>
    <mapper class="com.learn.dao.UserMapper"/>
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



> 值得注意的点

- Map传递参数，直接在sql中取出key即可【`parameterType="map"`】
- 对象传递参数，直接在sql语句中填写对象的属性即可【`parameterType="Object"`】
- 只有一个基本类型参数的情况下，可以直接在sql中取到【`parameterType="int"`，或者留空不写】
- 多个参数建议使用Map或注解



## Map实现模糊查改





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



## 关于多对一和一对多的问题

- 关联：`association` 【多对一】
- 集合：`collection` 【一对多】
- `javaType` & `ofType`
  - `javaType`：用于指定实体类中对象的属性
  - `ofType`：用于指定映射到List或者集合中的pojo类型，泛型中的约束类型



## 动态SQL

> 根据不同条件生产不同的SQL语句



### 字段名的转换

在mybatis-config中配置如下设置，可以将数据库中的下划线变量转变为Java实体类属性中的驼峰命名

```xml
<setting name="mapUnderscoreToCamelCase" value="true"/>
```



### if

在对应的`Mapper.xml`中配置以下文件，`test`当中写入`if`判断语句，这样可以提高该查询字段的复用性

```xml
<!--
	@where 1=1 这里传入的1 = 1不影响查询结果，而且为后面的sql语句拼接提供了便利
-->
<select id="queryBlogIF" parameterType="map" resultType="Blog">
    select * from blog where 1=1
    <if test="title != null">
        and title = #{title}
    </if>
    <if test="author != null">
        and author = #{author}
    </if>
</select>

<!--
	使用where标签，可以替代上面的where 1 = 1的语句
	若子句的开头为 “AND” 或 “OR”，where 元素也会将它们去除
-->
<select id="queryBlogIF" parameterType="map" resultType="Blog">
    select * from blog
    <where>
        <if test="title != null">
            and title = #{title}
        </if>
        <if test="author != null">
            and author = #{author}
        </if>
    </where>
</select>
```



### choose(when, otherwise)

有时候，我们不想使用所有的条件，而只是想从多个条件中选择一个使用。针对这种情况，MyBatis 提供了 `choose` 元素，它有点像 Java 中的 `switch` 语句

和if语句一样，test中写入判断条件，而`otherwise`类似于switch语句中的`default`

```xml
<select id="queryBlogChoose" parameterType="map" resultType="Blog">
    select * from blog
    <where>
        <choose>
            <when test="title != null">
                title = #{title}
            </when>
            <when test="author != null">
                and author = #{author}
            </when>
            <otherwise>
                and views = #{views}
            </otherwise>
        </choose>
    </where>
</select>
```



### trim(where, set)

如果 *where* 元素与你期望的不太一样，你也可以通过自定义 trim 元素来定制 *where* 元素的功能。比如，和 *where* 元素等价的自定义 trim 元素

*prefixOverrides* 属性会忽略通过管道符分隔的文本序列（注意此例中的空格是必要的）。上述例子会移除所有 *prefixOverrides* 属性中指定的内容，并且插入 *prefix* 属性中指定的内容

```xml
<trim prefix="WHERE" prefixOverrides="AND |OR ">
  ...
</trim>

<!--实例-->
<select id="queryBlogIF" parameterType="map" resultType="Blog">
    select * from blog
    <trim prefix="WHERE" prefixOverrides="AND |OR ">
        <if test="title != null">
            and title = #{title}
        </if>
        <if test="author != null">
            and author = #{author}
        </if>
    </trim>
</select>

<update id="updateAuthorIfNecessary">
    update Author
    <trim prefix="SET" suffixOverrides=",">
        <if test="username != null">username=#{username},</if>
        <if test="password != null">password=#{password},</if>
        <if test="email != null">email=#{email},</if>
        <if test="bio != null">bio=#{bio}</if>
    </trim>
    where id=#{id}
</update>
```



`set` 元素可以用于动态包含需要更新的列，忽略其它不更新的列

```xml
<update id="updataBlog" parameterType="map">
    update blog
    <set>
        <if test="title != null">
            title = #{title},
        </if>
        <if test="author != null">
            author = #{author}
        </if>
    </set>
    where id = #{id}
</update>
```



### SQL片段

有时候，我们会将一些重复的代码片段提取出来并复用

> 实例

```xml
<!--这里把这块常用的if判断代码片段提取了出来，并自定义id为if-title-author-->
<sql id="if-title-author">
    <if test="title != null">
        and title = #{title}
    </if>
    <if test="author != null">
        and author = #{author}
    </if>
</sql>
<select id="queryBlogIF" parameterType="map" resultType="Blog">
    select * from blog where 1=1
    <where>
        <!--在where标签中使用id为if-title-author的代码片段-->
        <include refid="if-title-author"></include>
    </where>
</select>
```

注意事项：

- 最好基于单表来定义SQL片段
- 在片段中不要出现where标签



### foreach

> 动态 SQL 的另一个常见使用场景是对集合进行遍历（尤其是在构建 IN 条件语句的时候）

可以达成的效果

```mysql
mysql> select * from user where 1 = 1 and (id = 1 or id = 2 or id = 3);
+----+--------+--------+
| id | name   | pwd    |
+----+--------+--------+
|  1 | 张强   | 777888 |
|  2 | 李四   | 123456 |
|  3 | 王五   | 123456 |
+----+--------+--------+
3 rows in set (0.01 sec)
```

语句解析

```xml
<select id="selectPostIn" resultType="domain.blog.Post">
  SELECT *
  FROM POST P
  WHERE ID in
  <foreach item="item" index="index" collection="list"
      open="(" separator="," close=")">
        #{item}
  </foreach>
</select>
```

> 实例

`Mapper`接口中创建一个方法，查询用户

```java
List<User> queryUserForeach(Map map);
```

`Mapper.xml`文件中的语句

```xml
<select id="queryUserForeach" parameterType="map" resultType="User">
	select * from User
    <where>
        <!--
			@select * from user where 1 = 1 and (id = 1 or id = 2 or id = 3);
			@collection Java中传入的集合，我们可以传入一个map，而map中存在一个集合
			@id sql中需要传入参数的属性
			@open sql语句中的查询条件语句的开始，如果有and之类的字符，后面必须添加空格--and (
			@separator sql语句中的分隔符--or
			@close sql语句中查询条件语句的结尾--)
		-->
    	<foreach collection="oid" item="id" open="and (" separator="or" close=")">
            <!--这里传入的id为遍历出来的id-->
        	id = #{id}
        </foreach>
    </where>
</select>
```

`Test`类中的测试

```java
@Test
public void queryUserForeach() {
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

    //创建一个存入id的集合
    ArrayList<Integer> arrayList = new ArrayList<Integer>();
    arrayList.add(1);
    arrayList.add(2);
    ...//可以添加更多的检索条件


    Map map = new HashMap();
    map.put("oid", arrayList);

    List<User> userList = userMapper.queryUserForeach(map);

    for (User user : userList) {
        System.out.println(user);
    }
}
```



## 缓存（Cache）

- 由于每次查询都需要连接数据库，损耗资源，因此我们可以将一次查询的结果存于内存当中，再次查询的时候就可以调用缓存而不需要重新查询
- 使用缓存可以减少和数据库的交互，从而减少系统开销，提高系统效率
- 缓存一般用于经常查询而不经常改变的数据



### Mybatis缓存

Mybatis系统中默认定义了两级缓存：**一级缓存**和**二级缓存**

- 默认情况下，只有一级缓存开启（`SqlSession`级别的缓存，也称为本地缓存）
- 二级缓存需要手动开启和配置，它是基于`namespace`级别的缓存
- 为了提高扩展性，Mybatis定义了缓存接口`Cache`，我们可以通过实现接口`Cache`来自定义二级缓存



### 一级缓存

> 一级缓存默认开启，只在一次*SqlSession*中有效，也就是拿到连接到关闭连接这个区间；一级缓存就是一个Map



#### 缓存失效情况

如果需要使用缓存，那么我们启用测试的时候**一定要开启日志**

- 查询不同的数据
- 增删改操作，可能会改变原来的数据，必定会刷新缓存
- 查询不同的Mapper.xml
- 手动清理缓存【`sqlSession.clearCache();`】



### 二级缓存

- 二级缓存也叫全局缓存，一级缓存作用域太低，所以诞生了二级缓存
- 基于`namespace`级别的缓存，一个命名空间对应一个二级缓存
- 工作机制
  - 一个会话查询一条数据，这个数据就会被放在当前会话的一级缓存当中
  - 如果当前会话关闭，这个会话对应的一级缓存也就失效；但是会话关闭，一级缓存中的数据会被保存到二级缓存当中
  - 新的会话查询信息，可以从二级缓存中获取内容
  - 不同的mapper查出的数据会放在字节对应的缓存中（map）

默认情况下，只启用了本地的会话缓存，它仅仅对一个会话中的数据进行缓存。 要启用全局的二级缓存，只需要在你的 SQL 映射文件中添加一行`<cache/>`

> 实例

```xml
<!--
	@eviction 缓存模式
	@flushInterval 每隔...ms刷新
	@size 缓存达到该长度后会自动清理
	@readOnly
-->
<cache
  eviction="FIFO"	
  flushInterval="60000"
  size="512"
  readOnly="true"/>
```



#### 使用二级缓存的步骤

- 开启全局缓存

  - 在`mybatis-config.xml`中插入如下语句

  - ```xml
    <settings>
        <setting name="cacheEnabled" value="true"/>
    </settings>
    ```

- 在对应的Mapper.xml中开启`<cache/>`标签，开启二级缓存

  - 当然，我们也可以在对应的sql语句标签中加入属性`useCache`来开启/关闭缓存

  - ```xml
    <select id="getUserById" resultType="user" parameterType="int" useCache="false">
        select * from user where id = #{id}
    </select>
    ```

- 如果我们缓存设置中不设置属性，只设置了`<cache/>`，那么，我们需要在实体类中序列化，否则会出现报错

  - ```java
    public class User implements Serializable
    ```



> 实例

```java
@Test
public void testCache() {
    SqlSession sqlSession1 = MybatisUtils.getSqlSession();
    SqlSession sqlSession2 = MybatisUtils.getSqlSession();

    UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
    User user1 = userMapper1.getUserById(1);
    System.out.println(user1);

    sqlSession1.close();//二级缓存在一级缓存结束后开启，因此需要先关闭一级缓存

    UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);//在这里会去读取二级缓存中的数据，不会重新加载
    User user2 = userMapper2.getUserById(1);
    System.out.println(user2);

    sqlSession2.close();
}
```



- 只要开启了二级缓存，那么在同一个`Mapper`中就会生效
- 所有数据都会存储在一级缓存中
- 只有会话提交或者关闭才会启用二级缓存



### 自定义缓存-ehcache

> ehcache是一种广泛使用的开源Java分布式缓存，主要面向通用缓存

#### 使用流程

- 导包

  - pom.xml文件中导入如下包：

  - ```xml
    <dependencies>
        <!--导入自定义缓存包-->
        <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-ehcache -->
        <dependency>
            <groupId>org.mybatis.caches</groupId>
            <artifactId>mybatis-ehcache</artifactId>
            <version>1.1.0</version>
        </dependency>
    </dependencies>
    ```

- 配置Mapper.xml文件中的`<cache>`标签实现缓存

  - `<cache type="org.mybatis.caches.ehcache.EhcacheCache"/>`

- 导入`ehcache.xml`配置文件

  - ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:noNamespaceSchemaLocation="http://ehcache.org/ehcache.xsd"
             updateCheck="false">
        <!--
           diskStore：为缓存路径，ehcache分为内存和磁盘两级，此属性定义磁盘的缓存位置。参数解释如下：
           user.home – 用户主目录
           user.dir  – 用户当前工作目录
           java.io.tmpdir – 默认临时文件路径
         -->
        <diskStore path="./tmpdir/Tmp_Ehcache"/>
        <!--
           defaultCache：默认缓存策略，当ehcache找不到定义的缓存时，则使用这个缓存策略。只能定义一个。
         -->
        <!--
          name:缓存名称。
          maxElementsInMemory:缓存最大数目
          maxElementsOnDisk：硬盘最大缓存个数。
          eternal:对象是否永久有效，一但设置了，timeout将不起作用。
          overflowToDisk:是否保存到磁盘，当系统宕机时
          timeToIdleSeconds:设置对象在失效前的允许闲置时间（单位：秒）。仅当eternal=false对象不是永久有效时使用，可选属性，默认值是0，也就是可闲置时间无穷大。
          timeToLiveSeconds:设置对象在失效前允许存活时间（单位：秒）。最大时间介于创建时间和失效时间之间。仅当eternal=false对象不是永久有效时使用，默认是0.，也就是对象存活时间无穷大。
          diskPersistent：是否缓存虚拟机重启期数据 Whether the disk store persists between restarts of the Virtual Machine. The default value is false.
          diskSpoolBufferSizeMB：这个参数设置DiskStore（磁盘缓存）的缓存区大小。默认是30MB。每个Cache都应该有自己的一个缓冲区。
          diskExpiryThreadIntervalSeconds：磁盘失效线程运行时间间隔，默认是120秒。
          memoryStoreEvictionPolicy：当达到maxElementsInMemory限制时，Ehcache将会根据指定的策略去清理内存。默认策略是LRU（最近最少使用）。你可以设置为FIFO（先进先出）或是LFU（较少使用）。
          clearOnFlush：内存数量最大时是否清除。
          memoryStoreEvictionPolicy:可选策略有：LRU（最近最少使用，默认策略）、FIFO（先进先出）、LFU（最少访问次数）。
          FIFO，first in first out，这个是大家最熟的，先进先出。
          LFU， Less Frequently Used，就是上面例子中使用的策略，直白一点就是讲一直以来最少被使用的。如上面所讲，缓存的元素有一个hit属性，hit值最小的将会被清出缓存。
          LRU，Least Recently Used，最近最少使用的，缓存的元素有一个时间戳，当缓存容量满了，而又需要腾出地方来缓存新的元素的时候，那么现有缓存元素中时间戳离当前时间最远的元素将被清出缓存。
       -->
        <defaultCache
                eternal="false"
                maxElementsInMemory="10000"
                overflowToDisk="false"
                diskPersistent="false"
                timeToIdleSeconds="1800"
                timeToLiveSeconds="259200"
                memoryStoreEvictionPolicy="LRU"/>
    
        <cache
                name="cloud_user"
                eternal="false"
                maxElementsInMemory="5000"
                overflowToDisk="false"
                diskPersistent="false"
                timeToIdleSeconds="1800"
                timeToLiveSeconds="1800"
                memoryStoreEvictionPolicy="LRU"/>
    
    </ehcache>
    ```

当前实际工作中使用缓存一般通过**Redis**实现