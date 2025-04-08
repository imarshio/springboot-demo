package com.marshio.demo.jetcache;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author marshio
 * @desc
 * @create 2023-04-11 10:54
 */
@SpringBootApplication
@EnableMethodCache(basePackages = "com.marshio.demo.jetcache")
public class JetCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(JetCacheApplication.class, args);
    }
}
