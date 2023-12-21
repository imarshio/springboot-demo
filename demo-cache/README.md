# Spring Cache

参考：https://docs.spring.io/spring-framework/docs/3.1.x/spring-framework-reference/html/cache.html

## 主要注解

### @EnableCaching

可以放在启动类，也可以放到缓存配置类

### @Cacheable

可以缓存，取决于缓存更新计算策略

### @CachePut

更新缓存

### @CacheEvict

缓存驱逐

### @Caching

一系列缓存操作，包括@Cacheable、@CachePut、@CacheEvict，目前测试只支持相同类型的操作，不支持混合操作类型.

如下
```java
    // 这样可以正常执行
    @Caching(
            put = {
                @CachePut(value = USER, key = "#user.id"),
                @CachePut(value = USER, key = "#user.name")
            }
    )
    public User updateUserById(User user) {
        log.info("用户信息更新：{}。", user);
        return user;
    }


    // 这样就只有evict的部分能执行成功
    @Caching(
            put = {
                 @CachePut(value = USER, key = "#user.id")
            },
            evict = {
                 @CacheEvict(value = USER, key = "#user.id")
            }
            
    )
    public User updateUserById(User user) {
        log.info("用户信息更新：{}。", user);
        return user;
    }
    // 可能是我目前使用的方式不对，
```

### @CacheConfig

提取一类缓存的更改配置


### @CachingConfigurer