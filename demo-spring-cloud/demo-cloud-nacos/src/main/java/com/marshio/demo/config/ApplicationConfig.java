package com.marshio.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;


@Component
@RefreshScope
@ConfigurationProperties(prefix = ApplicationConfig.APP)
public class ApplicationConfig {

    protected static final String APP = "app";
}
