# Mybatis

## 添加依赖

```xml

<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <!-- 版本需要自己去根据项目的sprint boot版本确定 -->
    <!-- 参考：https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/index.html -->
    <version>${mybatis.version}</version>
</dependency>
```

## Mybatis Generator

[quickstart](https://mybatis.org/generator/quickstart.html)

### 添加插件

```xml

<project>
    <build>
        <plugins>
            <plugin>
                <groupId>org.mybatis.generator</groupId>
                <artifactId>mybatis-generator-maven-plugin</artifactId>
                <version>1.3.7</version>
                <executions>
                    <execution>
                        <id>Generate MyBatis Artifacts</id>
                        <phase>generate-sources</phase>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <verbose>true</verbose>
                    <overwrite>true</overwrite>
                </configuration>
                <dependencies>
                    <dependency>
                        <!-- 添加数据库驱动 -->
                        <groupId>mysql</groupId>
                        <artifactId>mysql-connector-java</artifactId>
                        <version>8.0.21</version>
                    </dependency>
                </dependencies>
            </plugin>
        </plugins>
    </build>


</project>
```

### 添加配置文件

更多配置文件样例请参考官网[MyBatis Generator Quick Start Guide](https://mybatis.org/generator/quickstart.html)

> [!Note]
> 为了避免生成的文件会覆盖已经修改过的文件
> 建议单独建一个module来做generate或者将生成文件放在一个专门的文件夹中

```xml
<!DOCTYPE generatorConfiguration PUBLIC
        "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>
    <!-- MyBatis3Simple风格 -->
    <context id="simple" targetRuntime="MyBatis3Simple">

        <property name="javaFileEncoding" value="UTF-8"/>
        <!--生成mapper.xml时覆盖原文件-->
        <plugin type="org.mybatis.generator.plugins.UnmergeableXmlMappersPlugin"/>

        <commentGenerator>
            <!-- 是否去除自动生成的注释 true：是 ： false:否。 自动生成注释太啰嗦，可以编码扩展CommentGenerator -->
            <property name="suppressAllComments" value="true"/>
        </commentGenerator>

        <!-- 数据库连接 -->
        <jdbcConnection driverClass="com.mysql.cj.jdbc.Driver"
                        connectionURL="jdbc:mysql://106.15.104.240:3306/mybatis?useUnicode=true&amp;characterEncoding=utf8&amp;serverTimezone=Asia/Shanghai"
                        userId="mybatis"
                        password="3jq^edM#nW5J">
            <!-- 设置为true就只读取目标数据库下的表, 否则会优先读取到mysql的user表  -->
            <property name="nullCatalogMeansCurrent" value="true"/>
        </jdbcConnection>

        <!-- 生成entity的包名和位置 -->
        <javaModelGenerator targetPackage="com.marshio.demo.domain.entity" targetProject="src/main/java"/>

        <!-- 生成XML映射文件的包名和位置 -->
        <sqlMapGenerator targetPackage="mapper" targetProject="src/main/resources"/>

        <!-- 生成Mapper接口的包名和位置 -->
        <javaClientGenerator type="XMLMAPPER" targetPackage="com.marshio.demo.mapper" targetProject="src/main/java"/>

        <!-- 要生成对应表配置 -->
        <table tableName="user" domainObjectName="User">
            <!-- 自增主键列 -->
            <generatedKey column="id" sqlStatement="MYSQL" identity="true"/>
            <!-- &lt;!&ndash; tinyint映射为Integer &ndash;&gt; -->
            <!-- <columnOverride column="role" javaType="Integer" jdbcType="TINYINT"/> -->
            <columnOverride column="role" javaType="List" jdbcType="VARCHAR"
                            typeHandler="com.marshio.demo.handler.JsonTypeHandler"/>
        </table>

    </context>
</generatorConfiguration>

```

### 生成代码

命令行：

```shell
 java -jar mybatis-generator-core-x.x.x.jar -configfile \temp\generatorConfig.xml -overwrite
```

或者直接使用maven --> Plugins --> MyBatis Generator --> Generate

## 配置

### `@MapperScann`

```java

@MapperScan({
        // mapper接口的路径
        "com.marshio.demo.mapper",
})
@SpringBootApplication
public class MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }
}
```

### `application.yml`

```yaml
mybatis:
  mapper-locations: classpath:mapper/*.xml
  type-aliases-package: com.marshio.demo.domain.entity
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
    map-underscore-to-camel-case: true
    cache-enabled: true
    use-generated-keys: true
    default-fetch-size: 1000
    default-statement-timeout: 1000
    cache-miss-null-object: true
    lazy-loading-enabled: true
    aggressive-lazy-loading: true
    use-actual-column-names: true
    use-column-label: true
```

### 其他

Mybatis 支持将配置放在如下两个地方.

- `src/main/resources/application.yml`
- `src/main/resources/mybatis-config.xml`

我们需要区分`@MapperScan`注解和`mybatis.mapper-locations: classpath:mapper/*.xml` 两个配置的区别

- `@MapperScan`注解：适用于将`***Mapper.java`注入到bean容器内
- `mybatis.mapper-locations:`：用较少的代码完成动态生成SQL并解析结果及参数映射等一系列操作

更多配置详情可以参考[mybatis-spring-boot-autoconfigure](https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)

此时我们就可以运行代码了.
