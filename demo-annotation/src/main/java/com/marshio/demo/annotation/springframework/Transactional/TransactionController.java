package com.marshio.demo.annotation.springframework.Transactional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author marshio
 * @desc
 * @create 2023-12-26 16:41
 */
@RestController
@RequestMapping("/demo/annotation/transactional")
public class TransactionController {

    private final TransactionalService service;

    public TransactionController(TransactionalService service) {
        this.service = service;
    }

    @GetMapping("/rollback1")
    public Object rollback1(Boolean flag) {
        return service.rollback1(flag);
    }

    @GetMapping("/rollback2")
    public Object rollback2(Boolean flag) {
        return service.rollback2(flag);
    }
}
