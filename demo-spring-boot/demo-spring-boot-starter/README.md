## 如何自定义 `Spring Boot Starter` ?

> [!IMPORTANT]
> 
> 1、在 Spring Boot 2.7 版本之前，通过`META-INF/spring.factories`文件定义我们自动配置的类。
> 
> 2、在 Spring Boot 2.7 ~ Spring Boot 3.0 版本之间，是兼容了 `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`  和 `META-INF/spring.factories` 这两个文件
> 
> 3、在 Spring Boot 3.0 版本之后，只支持使用`META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports` 文件。

## Spring Boot 2.7 以前

1、新建 Maven 项目 `xxx-spring-boot-starter` ，并添加依赖

```pom
    <dependencies>
        <!-- 添加支持配置解析的依赖 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
        </dependency>

        <!-- 添加支持自动装配的依赖 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-autoconfigure</artifactId>
        </dependency>
        
    </dependencies>
```

2、 创建配置属性类（可选）

```java
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = DemoProperties.PREFIX)
public class DemoProperties {

    public static final String PREFIX = "demo";

    private String mapperLocations;

}

```

3、 创建自动配置类（重点）

```java
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DemoProperties.class)
public class DemoAutoConfiguration {

    private final DemoProperties properties;

    public DemoAutoConfiguration(DemoProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public DemoAutoConfigurationService customService() {
        return new DemoAutoConfigurationService(properties);
    }
}

```

4、 添加 `META-INF/spring.factories` 文件（重点）

在 `src/main/resources` 目录下添加 `META-INF/spring.factories` 文件，在其中定义需要自动装配的类

```text

org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
  com.marshio.demo.autoconfigure.DemoAutoConfiguration,\
  com.marshio.demo.autoconfigure.XxxAutoConfiguration
```

## Spring Boot 3.0 之后

与 Spring Boot 2.7 之前的区别是，在 Spring Boot 3.0 之后，只支持使用`META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports` 文件。

在 `src/main/resources` 目录下添加 `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports` 文件，在其中定义需要自动装配的类 

```text
com.marshio.demo.autoconfigure.DemoAutoConfiguration
com.marshio.demo.autoconfigure.XxxAutoConfiguration
```

## 验证

将自定义的 starter 发布到私服或者 maven 中央仓库，然后再自己的项目中引入即可。

具体代码可以查看 `demo-spring-boot-starter-test` 项目.