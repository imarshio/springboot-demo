package com.marshio.demo.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author marshio
 * @desc ...
 * @create 2024/6/7 12:09
 */

@Data
@ApiModel("用户信息")
public class UserVO {

    @ApiModelProperty(value = "用户id")
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "手机号")
    private String phoneNumber;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "注册时间")
    private Date registrationTime;

    @ApiModelProperty(value = "是否激活")
    private Boolean isActive;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "角色")
    private List<Integer> role;

    @ApiModelProperty(value = "简介")
    private String intro;
}
