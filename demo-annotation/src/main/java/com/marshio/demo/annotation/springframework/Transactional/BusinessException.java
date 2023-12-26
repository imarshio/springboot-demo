package com.marshio.demo.annotation.springframework.Transactional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author marshio
 * @desc
 * @create 2023-12-26 19:08
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException{
    private String code;
    private String message;
}
