package user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

/**
 * @author marshio
 * @desc
 * @create 2023-03-31 17:54
 */
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
