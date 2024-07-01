package com.marshio.demo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.marshio.demo.domain.entity.Doc;
import com.marshio.demo.domain.request.DocRequest;
import com.marshio.demo.domain.rest.PageRequest;
import com.marshio.demo.mapper.DocMapper;
import com.marshio.demo.service.IDocService;
import org.springframework.stereotype.Service;

/**
 * @author marshio
 * @desc ...
 * @create 2024/7/1 16:27
 */
@DS("doc")
@Service
public class DocServiceImpl extends ServiceImpl<DocMapper, Doc> implements IDocService {
    @Override
    public Page<Doc> page(PageRequest<DocRequest> request) {
        return this.getBaseMapper().selectPage(
                new Page<>(request.getPage().getPageNo(), request.getPage().getPageSize()),
                new LambdaQueryWrapper<Doc>()
                .eq(null != request.getQuery().getId(), Doc::getId, request.getQuery().getId()));
    }
}
