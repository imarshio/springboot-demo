---
icon: pen-to-square
date: 2024-03-22
order: 99
category:
  - springboot
title: springboot整合elasticsearch
tag:
  - elasticsearch
  - springboot
---

## 环境

- Java 11
- Maven 3.9.6
- springboot 2.3.5.RELEASE
- elasticsearch 7.10.0

> [!IMPORTANT]
>
> spring-boot与elasticsearch之间的版本兼容是一个很大的问题，你需要确保自己的spring-boot版本与elasticsearch版本兼容
>
> 版本兼容可以参考：https://docs.spring.io/spring-data/elasticsearch/reference/elasticsearch/versions.html

## 引入依赖

### spring boot框架

```xml

<dependencies>
    <!-- spring-boot-starter-data-elasticsearch是一个spring-boot-starter，代表他是一个支持自动装配的依赖，不需要手动配置 -->
    <!-- 如果你用的是spring-boot框架，那么你只需要引入spring-boot-starter-data-elasticsearch依赖即可，他已经包含了spring-data-elasticsearch依赖 -->
    <!-- https://docs.spring.io/spring-boot/docs/current/reference/html/data.html#data.nosql.elasticsearch -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
    </dependency>
</dependencies>
```

### spring data

```xml

<dependencies>
    <!-- 支持Elasticsearch的spring-data组件 -->
    <dependency>
        <groupId>org.springframework.data</groupId>
        <artifactId>spring-data-elasticsearch</artifactId>
    </dependency>
</dependencies>
```

## 配置

```yaml
spring:
  elasticsearch:
    rest:
      urls: http://localhost:19200
#    option 可选配置
#    index-name: ${spring.application.name}
```

## 实战

### 实体类

```java
package com.marshio.demo.elasticsearch.es.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author marshio
 * @desc es 存储的实体类
 * @create 2024/4/15 17:54
 */
@Data
@Document(indexName = "#{elasticsearchProperties.indexName}")
public class Doc {
    @Field(type = FieldType.Text)
    private String title;
    @Field(type = FieldType.Text)
    private String content;
    @Id
    private String id;
}
```

### 服务层

```java

```

## 疑问

### ElasticsearchRepository 与 RestHighLevelClient 的区别是什么？

### TermQuery与MatchQuery的区别

TermQuery

- 精确查询，不分词

MatchQuery

- 模糊查询，分词