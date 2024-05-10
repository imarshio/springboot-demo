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
    private String importance;
    private double informationScore;
    private boolean contentFiltered;
    private String source;
    @Field(type = FieldType.Text)
    private String title;
    @Field(type = FieldType.Text)
    private String content;
    private int newsType;
    private long dataId;
    private int titleLength;
    private int briefLength;
    private String supplier;
    private String pushDesc;
    private String brief;
    private String updatedBy;
    private boolean original;
    private int nature;
    private String author;
    private int pushStatus;
    @Id
    private String globalId;
    private String auditor;
    private int weight;
    private String url;
    private String webSource;
    private boolean deleted;
    private String createdBy;
    private String timeliness;
    private double timelinessScore;
    private int auditStatus;
    private int contentLength;
    private String auditDesc;
    private String information;
    private String _class;
    private double newsScore;
}