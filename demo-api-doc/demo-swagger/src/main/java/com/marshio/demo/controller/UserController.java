package com.marshio.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.marshio.demo.converter.UserConverter;
import com.marshio.demo.domain.request.PageRequest;
import com.marshio.demo.domain.request.UserRequest;
import com.marshio.demo.domain.rest.PageResponse;
import com.marshio.demo.domain.rest.Response;
import com.marshio.demo.domain.vo.UserVO;
import com.marshio.demo.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public Response<UserVO> getUserById(@PathVariable Integer id) {
        return Response.success(UserConverter.INSTANCE.toVO(userService.getUserById(id)));
    }

    @PostMapping("/list")
    @ApiOperation("分页查询用户--方式1")
    public PageResponse<UserVO> getUsers(@RequestBody PageRequest<UserRequest> request) {
        IPage<UserVO> page = userService.getUsers(request).convert(UserConverter.INSTANCE::toVO);
        return PageResponse.success(new Page<UserVO>(page.getCurrent(), page.getSize()).setRecords(page.getRecords()));
    }

    @PostMapping("/add")
    @ApiOperation("添加用户")
    public Response<Boolean> addUser(@RequestBody UserVO userVO) {
        return Response.success(userService.addUser(UserConverter.INSTANCE.toEntity(userVO)));
    }

}
