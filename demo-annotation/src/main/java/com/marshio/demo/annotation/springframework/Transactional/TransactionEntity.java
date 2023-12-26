package com.marshio.demo.annotation.springframework.Transactional;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author marshio
 * @desc 实体类
 * @create 2023-12-26 17:01
 */
@Data
@TableName("user")
public class TransactionEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
