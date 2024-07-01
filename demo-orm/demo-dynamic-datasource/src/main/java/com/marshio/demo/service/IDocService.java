package com.marshio.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.marshio.demo.domain.entity.Doc;
import com.marshio.demo.domain.request.DocRequest;
import com.marshio.demo.domain.rest.PageRequest;

/**
 * @author marshio
 * @desc ...
 * @create 2024/7/1 16:27
 */
public interface IDocService {
    Page<Doc> page(PageRequest<DocRequest> request);
}
