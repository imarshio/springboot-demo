package com.marshio.demo.domain.rest;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author marshio
 * @desc ...
 * @create 2024/6/25 18:36
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Response<T> extends R {

    private T data;

    public Response(Integer code, String status, String message, T data) {
        super(code, status, message);
        this.data = data;
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(200, R.SUCCESS, "操作成功", data);
    }
}
