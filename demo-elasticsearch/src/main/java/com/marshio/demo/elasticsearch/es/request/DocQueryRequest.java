package com.marshio.demo.elasticsearch.es.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author marshio
 * @desc ...
 * @create 2024/4/15 22:33
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DocQueryRequest extends BasePageQuery{


    private String globalId;
}
