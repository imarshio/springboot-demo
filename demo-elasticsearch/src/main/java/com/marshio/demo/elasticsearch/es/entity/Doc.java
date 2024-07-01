package com.marshio.demo.elasticsearch.es.entity;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author marshio
 * @desc es 存储的实体类
 * @create 2024/4/15 17:54
 */
@Data
@FieldNameConstants
@Document(indexName = "#{elasticsearchProperties.indexName}")
public class Doc {
    private String source;
    @Field(type = FieldType.Text)
    private String title;
    @Field(type = FieldType.Text)
    private String content;
    private String author;
    @Id
    private String globalId;

}