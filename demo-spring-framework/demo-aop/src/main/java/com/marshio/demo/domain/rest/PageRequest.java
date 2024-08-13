package com.marshio.demo.domain.rest;


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

    private PageInfo page;

    private List<OrderItem> orders = new ArrayList<>();
    private T query;

}
