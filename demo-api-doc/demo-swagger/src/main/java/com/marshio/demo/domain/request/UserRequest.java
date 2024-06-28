package com.marshio.demo.domain.request;

import com.marshio.demo.domain.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * @author marshio
 * @desc ...
 * @create 2024/6/7 12:09
 */

@Data
@ApiModel("用户请求信息")
@EqualsAndHashCode(callSuper = true)
public class UserRequest extends User {

    @ApiModelProperty("用户id列表")
    List<Integer> ids;
}
