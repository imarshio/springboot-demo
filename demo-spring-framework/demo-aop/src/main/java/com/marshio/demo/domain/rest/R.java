package com.marshio.demo.domain.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author marshio
 * @desc ...
 * @create 2024/6/25 17:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class R implements Serializable {

    public static String SUCCESS = "success";
    public static String FAIL = "fail";

    private static final long serialVersionUID = -35053716264503676L;

    private Integer code;
    private String status;
    private String message;
}
