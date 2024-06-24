package com.marshio.demo.domain.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author marshio
 * @desc ...
 * @create 2024/6/7 12:09
 */

@Data
public class UserVO {

    private Integer id;

    private String username;

    private String email;

    private String password;

    private Date birthday;

    private String phoneNumber;

    private String address;

    private Date registrationTime;

    private Boolean isActive;

    private String gender;

    private List<Integer> role;

    private String intro;
}
