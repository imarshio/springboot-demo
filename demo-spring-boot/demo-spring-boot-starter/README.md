## 如何自定义 `spring boot starter` ?

> [!IMPORTANT]
> 1、在spring boot2.7版本之前，通过`META-INF/spring.factories`文件定义我们自动配置的类。
> 2、在spring boot2.7~spring boot3.0版本之间，是兼容了 `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`  和`META-INF/spring.factories` 这两个文件
> 3、在spring boot3.0版本之后，只支持使用`META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports` 文件。

## 规范

1、新建模块 `xxx-spring-boot-starter` 用于管理依赖
2、新建模块 `xxx-spring-boot-autoconfigure` 管理自动装配的逻辑，即核心代码
