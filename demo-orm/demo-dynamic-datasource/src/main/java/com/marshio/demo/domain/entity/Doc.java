package com.marshio.demo.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author marshio
 * @desc ...
 * @create 2024/7/1 16:15
 */
@Data
@TableName(value = "doc", autoResultMap = true)
public class Doc {
    @TableId
    private Integer id;

    private String title;

    private String content;

    private String supplier;

    private String url;
}
