package com.marshio.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.marshio.demo.domain.entity.User;
import com.marshio.demo.domain.request.UserRequest;
import com.marshio.demo.domain.rest.PageRequest;


/**
 * @author marshio
 * @desc ...
 * @create 2024/6/7 12:11
 */
public interface IUserService extends IService<User> {

    User getUserById(Integer id);

    Page<User> getUsers(PageRequest<UserRequest> request);

    Boolean addUser(User entity);
}
