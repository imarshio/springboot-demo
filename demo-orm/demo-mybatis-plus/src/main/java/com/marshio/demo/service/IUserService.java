package com.marshio.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.marshio.demo.domain.entity.User;

import java.util.List;

/**
 * @author marshio
 * @desc <a href="https://baomidou.com/guides/data-interface/">Mybatis-plus data interface</a>
 * @create 2024/6/7 12:11
 */
public interface IUserService extends IService<User> {

    List<User> getAllUser();
}
