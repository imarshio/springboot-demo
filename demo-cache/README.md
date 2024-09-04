## Spring Cache

参考：https://docs.spring.io/spring-framework/docs/3.1.x/spring-framework-reference/html/cache.html

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

## Redis 的三大客户端

- [jedis](https://github.com/redis/jedis)
- [redisson](https://github.com/redisson/redisson)
- [lettuce](https://github.com/redis/lettuce)

### jedis

优点：

- 支持全面的 Redis 操作特性（可以理解为API比较全面）。

缺点：

- 使用阻塞的 I/O，且其方法调用都是同步的，程序流需要等到 sockets 处理完 I/O 才能执行，不支持异步
- Jedis 客户端实例不是线程安全的，所以需要通过连接池来使用 Jedis。

### redisson

优点：

- 使用者对 Redis 的关注分离，可以类比 Spring 框架，这些框架搭建了应用程序的基础框架和功能，提升开发效率，让开发者有更多的时间来关注业务逻辑；
- 提供很多分布式相关操作服务，例如，分布式锁，分布式集合，可通过 Redis 支持延迟队列等。
- Redisson 基于 Netty 框架的事件驱动的通信层，其方法调用是异步的。
- Redisson 的 API 是线程安全的，所以可以操作单个 Redisson 连接来完成各种操作

缺点：

- Redisson 对字符串的操作支持比较差。

### lettuce

优点：

- 支持同步异步通信模式；
- Lettuce 的 API 是线程安全的，如果不是执行阻塞和事务操作，如 `BLPOP` 和 `MULTI/EXEC` ，多个线程就可以共享一个连接。