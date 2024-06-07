package com.marshio.demo.service;

import com.marshio.demo.domain.entity.User;
import com.marshio.demo.domain.vo.UserVO;

import java.util.List;

/**
 * @author marshio
 * @desc ...
 * @create 2024/6/7 12:11
 */
public interface IUserService {

    User getUserById(Integer id);

    List<User> getAllUser();
}
