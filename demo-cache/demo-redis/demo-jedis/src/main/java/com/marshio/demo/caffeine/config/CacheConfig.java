package com.marshio.demo.caffeine.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.TimeUnit;

/**
 * @author marshio
 * @desc
 * @create 2023-03-31 13:04
 */
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
     * Primary声明默认缓存为这个cacheManager
     */
    @Bean
    @Primary
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
