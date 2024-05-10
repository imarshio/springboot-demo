package com.marshio.demo.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author marshio
 * @desc ...
 * @create 2024/4/15 17:55
 */
@SpringBootApplication(scanBasePackages = "com.marshio.demo")
// @EnableElasticsearchRepositories(basePackages = "com.marshio.demo.elasticsearch.es")
public class ElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchApplication.class);
    }
}
