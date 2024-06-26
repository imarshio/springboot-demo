package com.marshio.demo.domain.rest;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author marshio
 * @desc ...
 * @create 2024/6/25 18:17
 */
@Data
@AllArgsConstructor
public class PageInfo implements Serializable {

    private static final long serialVersionUID = -8013652284204270335L;

    private Long pageNo;
    private Long pageSize;
    private Long total;
    private Long current;
}
