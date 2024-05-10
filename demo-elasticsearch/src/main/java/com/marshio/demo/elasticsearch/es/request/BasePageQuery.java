package com.marshio.demo.elasticsearch.es.request;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @author marshio
 * @desc ...
 * @create 2024/4/16 9:38
 */
@Data
public class BasePageQuery {

    private Integer pageNo = 1;

    private Integer pageSize = 10;

    @Size(max = 100)
    private String orderBy;
}
