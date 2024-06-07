package com.marshio.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author marshio
 * @desc ...
 * @create 2024/6/7 14:43
 */
public class JacksonConfig {

    public static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        // config
    }
}
