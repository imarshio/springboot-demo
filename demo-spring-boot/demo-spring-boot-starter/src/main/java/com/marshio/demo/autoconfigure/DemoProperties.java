package com.marshio.demo.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author marshio
 * @desc ...
 * @create 2024/8/21 18:22
 */
@Data
@ConfigurationProperties(prefix = DemoProperties.PREFIX)
public class DemoProperties {

    public static final String PREFIX = "demo";

    private String mapperLocations;

}
