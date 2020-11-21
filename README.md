# mybatis_project



## 启动流程

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

  

## 项目目录

- mybatis_study(父类项目文件夹)
  - mybatis-01：子类项目文件夹
    - src
      - main
        - java：用于存放Java代码的地方
          - com.learn
            - dao
            - utils：存放工具类的文件夹
        - resources：存放配置文件的文件夹
          - `mybatis-config.xml`：里面存放了JDBC的参数
    - `pom.xml`：子级maven依赖，继承父级
  - `pom.xml`：父级maven依赖
