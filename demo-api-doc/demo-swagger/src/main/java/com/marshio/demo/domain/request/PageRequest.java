package com.marshio.demo.domain.request;


import com.baomidou.mybatisplus.core.metadata.OrderItem;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author marshio
 * @desc ...
 * @create 2024/6/25 10:23
 */
@Data
public class PageRequest<T> {

    private Integer pageNo = 1;
    private Integer pageSize = 10;
    private Integer maxPageSize = 100;
    private Long total;

    private List<OrderItem> orders = new ArrayList<>();
    private T query;

}
