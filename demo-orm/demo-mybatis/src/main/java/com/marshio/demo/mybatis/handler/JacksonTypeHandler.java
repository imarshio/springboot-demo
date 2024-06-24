package com.marshio.demo.mybatis.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.marshio.demo.config.JacksonConfig;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.util.Assert;

/**
 * @author marshio
 * @desc ...
 * @create 2024/6/7 14:31
 */
@MappedTypes({Object.class})
@MappedJdbcTypes({JdbcType.VARCHAR})
public class JacksonTypeHandler extends JsonTypeHandler<Object> {

    private final Class<?> type;

    public JacksonTypeHandler(Class<?> type) {
        Assert.notNull(type, "Type argument cannot be null");
        this.type = type;
    }

    @Override
    protected Object parse(String json) {
        try {
            return JacksonConfig.OBJECT_MAPPER.readValue(json, this.type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected String toJson(Object obj) {
        try {
            return JacksonConfig.OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
