package com.marshio.demo.controller;

import com.marshio.demo.domain.entity.Doc;
import com.marshio.demo.domain.request.DocRequest;
import com.marshio.demo.domain.rest.PageRequest;
import com.marshio.demo.domain.rest.PageResponse;
import com.marshio.demo.service.IDocService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author marshio
 * @desc ...
 * @create 2024/7/1 16:29
 */
@Controller
@RequestMapping("/doc")
@RequiredArgsConstructor
public class DocController {

    private final IDocService docService;

    @PostMapping("/list")
    public ResponseEntity<PageResponse<Doc>> list(@RequestBody PageRequest<DocRequest> request) {
        return ResponseEntity.ok(PageResponse.success(docService.page(request)));
    }
}
