package com.marshio.demo.controller;

import com.marshio.demo.converter.UserConverter;
import com.marshio.demo.domain.vo.UserVO;
import com.marshio.demo.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author marshio
 * @desc ...
 * @create 2024/6/7 12:21
 */
@Api(tags = "用户接口", value = "用户接口value")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    @GetMapping("/{id}")
    @ApiOperation("根据id查询用户")
    public UserVO getUserById(@PathVariable Integer id) {
        return UserConverter.INSTANCE.toVO(userService.getUserById(id));
    }

    @RequestMapping("/list")
    public List<UserVO> getAllUser() {
        return UserConverter.INSTANCE.toVO(userService.getAllUser());
    }

    @PostMapping("/add")
    public Boolean addUser(@RequestBody UserVO userVO) {
        return userService.addUser(UserConverter.INSTANCE.toEntity(userVO));
    }

}
