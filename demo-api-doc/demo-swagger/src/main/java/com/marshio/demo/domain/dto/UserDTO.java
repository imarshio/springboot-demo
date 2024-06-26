package com.marshio.demo.domain.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author marshio
 * @desc ...
 * @create 2024/6/25 11:08
 */

@Data
public class UserDTO {
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
