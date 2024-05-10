package com.marshio.demo.elasticsearch.es.controller;

import com.marshio.demo.elasticsearch.es.entity.Doc;
import com.marshio.demo.elasticsearch.es.request.DocQueryRequest;
import com.marshio.demo.elasticsearch.es.service.DocEsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author marshio
 * @desc ...
 * @create 2024/4/15 22:47
 */
@RestController
@RequestMapping(value = "/demo/es")
@RequiredArgsConstructor
public class DocController {

    private final DocEsService docEsService;

    @PostMapping("/page")
    public Page<Doc> pageList(@RequestBody DocQueryRequest query) throws IOException {
        return docEsService.search(query, PageRequest.of(query.getPageNo() - 1, query.getPageSize()));
    }

    @GetMapping("/demo")
    public String dmeo() throws IOException {
        return "demo";
    }
}
