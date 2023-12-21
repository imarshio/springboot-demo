# Caffeine

官网：https://github.com/ben-manes/caffeine

## 介绍

参考：https://github.com/ben-manes/caffeine/wiki

Caffeine is a high performance, near optimal caching library. 

Caffeine provides an in-memory cache using a Google Guava inspired API. The improvements draw on our experience designing Guava's cache and ConcurrentLinkedHashMap.

Caffeine是一款高性能、几近最佳的缓存库。

Caffeine使用Google Guava启发的API提供了一个内存中的缓存。这些改进借鉴了我们设计Guava的缓存和ConcurrentLinkedHashMap的经验。


## 使用

### 依赖

```xml
        <dependency>
            <groupId>com.github.ben-manes.caffeine</groupId>
            <artifactId>caffeine</artifactId>
        </dependency>
```

### 配置

配置有两种方式，一种是通过application.yml配置文件使其生效，另一种是通过自定义的config类进行加载。

#### 配置文件

```yaml
spring:
  cache:
    # 配置缓存类型为caffeine   
    type: caffeine
    caffeine:
      # 缓存初始大小为10，最大大小为200，过期时间为5min
      spec: initialCapacity=10,maximumSize=200,expireAfterWrite=5m
```

#### 自定义config类

```java
@Data
@EnableCaching
@Configuration
@ConfigurationProperties(prefix = "app.caffeine")
public class CacheConfig {

    private long ttl;

    private int initCapacity;

    private int maximumSize;

    private String spec;

    /**
     * caffeine缓存管理器
     *
     * @return cacheManager bean对象注入Spring容器，bean的name默认为方法名称
     */
    @Bean
    public CacheManager caffeineCacheManager() {
        // 先定义caffeine缓存
        Caffeine<Object, Object> caffeine = Caffeine.newBuilder()
                .initialCapacity(initCapacity)
                .maximumSize(maximumSize)
                .expireAfterWrite(ttl, TimeUnit.MINUTES);

        // 缓存管理器
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(caffeine);

        // spec,详细可参考：com.github.benmanes.caffeine.cache.CaffeineSpec
        cacheManager.setCaffeineSpec(CaffeineSpec.parse(spec));
        return cacheManager;
    }

}
```

### 使用

```java
// CacheConfig：可以提取公共配置，如cacheName，key
@Slf4j
@Service
@CacheConfig(cacheNames = {"USER"})
public class UserService {

    private final static String USER = "USER";

    /**
     * Cacheable：将方法的结果放到缓存中，value参数是缓存的name，key是缓存的key
     */
    @Cacheable(value = USER, key = "#id")
    public User getUserById(long id) {
        log.info("获取id为{}的用户。", id);
        return new User(id, "男", "mas");
    }

    /**
     * CacheEvict：驱逐缓存
     */
    @CacheEvict(value = USER, key = "#id")
    public Boolean deleteUserById(long id) {
        log.info("删除USER缓存中key为{}的用户", id);
        return true;
    }

    /**
     * CachePut：将结果缓存，value参数是缓存的name，key是缓存的key
     */
    @CachePut(value = USER, key = "#user.id")
    public User addUser(User user) {
        log.info("新增用户：{}。", user);
        return user;
    }

    /**
     * Caching：支持一系列缓存操作，目前测试只支持相同的操作类型，但是官方文档说支持多种类型的操作类型，不知道是哪里的用法不对
     */
    @Caching(
            put = {
                    @CachePut(value = USER, key = "#user.id")
            }
    )
    public User updateUserById(User user) {
        log.info("用户信息更新：{}。", user);
        return user;
    }
}
```