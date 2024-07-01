package com.marshio.demo.domain.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("响应信息")
@NoArgsConstructor
@AllArgsConstructor
public class R implements Serializable {

    public static String SUCCESS = "success";
    public static String FAIL = "fail";

    private static final long serialVersionUID = -35053716264503676L;

    @ApiModelProperty(value = "响应码")
    private Integer code;
    @ApiModelProperty(value = "响应状态")
    private String status;
    @ApiModelProperty(value = "响应信息")
    private String message;
}
