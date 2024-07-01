package com.marshio.demo.domain.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @author marshio
 * @desc mybatis plus枚举示例
 * @create 2024/6/11 10:06
 */
@Getter
public enum Gender implements IEnum<String> {
    MALE("MALE"),
    FEMALE("FEMALE"),
    OTHER("OTHER");

    // 存储在数据库的值
    // 可以使用注解@EnumValue
    // @EnumValue
    // @JsonValue 用来将序列化枚举值为前端返回值
    @JsonValue
    private final String code;

    Gender(String gender) {
        this.code = gender;
    }

    // 也可以继承IEnum<> 然后实现getValue()方法
    @Override
    public String getValue() {
        return code;
    }
}
