package com.marshio.demo.domain.rest;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author marshio
 * @desc ...
 * @create 2024/6/25 17:02
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class PageResponse<T> extends R {

    private List<T> data;
    private PageInfo page;

    public PageResponse(Integer code, String status, String message, List<T> data, PageInfo page) {
        super(code, status, message);
        this.data = data;
        this.page = page;
    }

    public static <T> PageResponse<T> success(Page<T> page) {
        return new PageResponse<>(200, R.SUCCESS, "操作成功", page.getRecords(),
                new PageInfo(page.getCurrent(), page.getSize(), page.getTotal(), page.getCurrent()));
    }

}
