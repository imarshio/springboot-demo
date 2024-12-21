## 简介

## 引入依赖

不知道具体选用哪个版本的可以通过 [官网](https://spring.io/projects/spring-cloud#learn)
和 [Maven 中央仓库](https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-dependencies) 查看。

```xml

<project>
    <!-- 指定spring cloud的版本 -->
    <dependencyManagement>
        <dependencies>
            <!-- https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-dependencies -->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>2023.0.3</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

        </dependencies>
    </dependencyManagement>

    <!-- 实际引入的依赖 -->
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-gateway</artifactId>
        </dependency>
    </dependencies>
</project>
```

### 排除 `spring-boot-starter-web`

`Spring Cloud Gateway` 是通过 `Netty+Webflux` 实现的 `Web` 服务器，跟`Spring-boot-starter-web`是冲突的，不排除是启动不了的哦。

```log
Description:

Parameter 0 of method modifyRequestBodyGatewayFilterFactory in org.springframework.cloud.gateway.config.GatewayAutoConfiguration required a bean of type 'org.springframework.http.codec.ServerCodecConfigurer' that could not be found.


Action:

Consider defining a bean of type 'org.springframework.http.codec.ServerCodecConfigurer' in your configuration.
```

### 启动日志

```log
2024-08-12 17:32:45.950  INFO 33508 --- [           main] c.m.demo.SpringCloudGatewayApplication   : No active profile set, falling back to default profiles: default
2024-08-12 17:32:47.011  INFO 33508 --- [           main] o.s.cloud.context.scope.GenericScope     : BeanFactory id=ce4e049d-b769-3b6c-8545-0404abadbc84
2024-08-12 17:32:47.853  INFO 33508 --- [           main] o.s.c.g.r.RouteDefinitionRouteLocator    : Loaded RoutePredicateFactory [After]
2024-08-12 17:32:47.853  INFO 33508 --- [           main] o.s.c.g.r.RouteDefinitionRouteLocator    : Loaded RoutePredicateFactory [Before]
2024-08-12 17:32:47.853  INFO 33508 --- [           main] o.s.c.g.r.RouteDefinitionRouteLocator    : Loaded RoutePredicateFactory [Between]
2024-08-12 17:32:47.853  INFO 33508 --- [           main] o.s.c.g.r.RouteDefinitionRouteLocator    : Loaded RoutePredicateFactory [Cookie]
2024-08-12 17:32:47.854  INFO 33508 --- [           main] o.s.c.g.r.RouteDefinitionRouteLocator    : Loaded RoutePredicateFactory [Header]
2024-08-12 17:32:47.854  INFO 33508 --- [           main] o.s.c.g.r.RouteDefinitionRouteLocator    : Loaded RoutePredicateFactory [Host]
2024-08-12 17:32:47.854  INFO 33508 --- [           main] o.s.c.g.r.RouteDefinitionRouteLocator    : Loaded RoutePredicateFactory [Method]
2024-08-12 17:32:47.854  INFO 33508 --- [           main] o.s.c.g.r.RouteDefinitionRouteLocator    : Loaded RoutePredicateFactory [Path]
2024-08-12 17:32:47.854  INFO 33508 --- [           main] o.s.c.g.r.RouteDefinitionRouteLocator    : Loaded RoutePredicateFactory [Query]
2024-08-12 17:32:47.854  INFO 33508 --- [           main] o.s.c.g.r.RouteDefinitionRouteLocator    : Loaded RoutePredicateFactory [ReadBody]
2024-08-12 17:32:47.854  INFO 33508 --- [           main] o.s.c.g.r.RouteDefinitionRouteLocator    : Loaded RoutePredicateFactory [RemoteAddr]
2024-08-12 17:32:47.854  INFO 33508 --- [           main] o.s.c.g.r.RouteDefinitionRouteLocator    : Loaded RoutePredicateFactory [Weight]
2024-08-12 17:32:47.854  INFO 33508 --- [           main] o.s.c.g.r.RouteDefinitionRouteLocator    : Loaded RoutePredicateFactory [CloudFoundryRouteService]
2024-08-12 17:32:48.547  INFO 33508 --- [           main] o.s.b.web.embedded.netty.NettyWebServer  : Netty started on port(s): 8080
2024-08-12 17:32:48.918  INFO 33508 --- [           main] c.m.demo.SpringCloudGatewayApplication   : Started SpringCloudGatewayApplication in 4.42 seconds (JVM running for 5.459)

```

## 配置

具体有哪些配置可以配置可以查看源码 `org.springframework.cloud.gateway.config.GatewayProperties`。

### Filter

#### 启用访问日志

通过添加启动参数`-Dreactor.netty.http.server.accessLogEnable=true`来启用访问日志。

输出示例

```java
```



#### 自定义全局过滤器

```java
package com.marshio.demo.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
@Slf4j
public class LogFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Global filtering");
        return chain.filter(exchange);
    }
}

```

#### 跨域配置

##### 通过YAML

[官网地址](https://docs.spring.io/spring-cloud-gateway/reference/spring-cloud-gateway/cors-configuration.html)

```yam
spring:
  cloud:
    gateway:
      globalcors:
        cors-configurations:
          '[/**]':
            allowedOrigins: '*'
            allowedMethods:
            - GET
```



### Route

请求转发，适用于很多场景，如灰度发布，接口包装

```yaml
spring:
  cloud:
    gateway:
      routes:
        # 如下配置表明，当很满足 Path=/v1/** 条件时，将请求转发给 "http://127.0.0.1:18080"，所以实际请求为 http://127.0.0.1:18080/v1/**
        - id: "server_old"
          uri: "http://127.0.0.1:18080"
          predicates:
            - Path=/v1/**
        # 如下配置表明，当很满足 Path=/v2/** 条件时，将请求转发给 "http://127.0.0.1:18081"，所以实际请求为 http://127.0.0.1:18081/v2/**
        - id: "server_new"
          uri: "http://127.0.0.1:18081"
          predicates:
            - Path=/v2/**
```

> 一个程序启动两个副本，可以通过 `-Dserver.port=18080` 来指定不同的端口。

### Predicates

断言器

#### 自定义路由断言工厂

```java
package com.marshio.demo.factory;

import lombok.Data;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
public class AuthCheckRoutePredicateFactory extends AbstractRoutePredicateFactory<AuthCheckRoutePredicateFactory.Config> {

    public static final String PARAM_KEY = "param";

    public static final String REGEXP_KEY = "regexp";

    public AuthCheckRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(PARAM_KEY, REGEXP_KEY);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return (GatewayPredicate) serverWebExchange -> {
            if (config.getName().equals(PARAM_KEY)) {
                return true;
            }
            return false;
        };
    }

    @Validated
    @Data
    public static class Config {
        private String name;
    }
}

```

## 整合sentinel

### 添加依赖

```pom
        <!-- SpringCloud Alibaba Sentinel -->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
        </dependency>

        <!-- SpringCloud Alibaba Sentinel Gateway -->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-alibaba-sentinel-gateway</artifactId>
        </dependency>
```



### 配置

> 注：在springcloud 2.1.6 之前，是需要注册一些 config bean才能正常使用，新依赖中由`com.alibaba.cloud.sentinel.gateway.scg.SentinelSCGAutoConfiguration`这个类帮我们完成了这个功能。

```yaml
    sentinel:
      transport:
        dashboard: localhost:8858
```



