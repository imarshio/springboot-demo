# 介绍

本项目用于演示个人学习Spring全家桶相关内容时的案例。

spring提供了[全局搜索](https://docs.spring.io/spring-boot/search.html)，如果你找不到的话，还是需要费一番功夫才能找到呢

# Spring AOP

# Spring IoC

# Spring Mail

# Spring MySQL

# Spring Postgres

# Spring Log

## log4j

## logback

# Spring orm

## mybatis

## mybatis-plus

## dynamic datasource

# Spring Cache

## spring cache annotation

## redis template

## Caffeine

## jedis

## jetcache

## lettuce

## redisson

# Spring Kafka

# Spring Cloud

## Gateway

Spring Cloud Gateway 底层采用了 Netty 网络通讯框架。

当我们向服务器发起一个请求时，如果你没有前置的`LB（Load Balance）`层，那么请求就会首先到达 `Gateway`。

### 功能

- 负载均衡
- 服务发现，注册中心
- 限流
- 路由和转发
- 数据过滤和监控
- 协议转换
- 访问控制（鉴权）

### 特性

- 基于 Spring Framework 5，Project Reactor 和 Spring Boot 2.0

- 集成 Hystrix 断路器

- 集成 Spring Cloud DiscoveryClient

- Predicates 和 Filters 作用于特定路由，易于编写的 Predicates 和 Filters

- 具备一些网关的高级功能：动态路由、限流、路径重写

### 核心组件

#### 路由(Router)

#### 断言(Predicate)

#### 过滤器(Filter)

### 负载均衡配置

### 限流配置

### 路由配置
