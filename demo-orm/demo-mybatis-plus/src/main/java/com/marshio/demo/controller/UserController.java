package com.marshio.demo.controller;

import com.marshio.demo.converter.UserConverter;
import com.marshio.demo.domain.vo.UserVO;
import com.marshio.demo.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author marshio
 * @desc ...
 * @create 2024/6/7 12:21
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    @GetMapping("/{id}")
    public UserVO getUserById(@PathVariable Integer id) {
        return UserConverter.INSTANCE.toVO(userService.getById(id));
    }

    @RequestMapping("/list")
    public List<UserVO> getAllUser() {
        return UserConverter.INSTANCE.toVO(userService.getAllUser());
    }

}
