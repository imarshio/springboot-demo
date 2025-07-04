package com.marshio.demo.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author marshio
 * @desc ...
 * @create 2024/8/21 18:16
 */
@Configuration
@EnableConfigurationProperties(DemoProperties.class)
public class DemoAutoConfiguration {

    private final DemoProperties properties;

    public DemoAutoConfiguration(DemoProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public DemoAutoConfigurationService customService() {
        return new DemoAutoConfigurationService(properties);
    }
}
