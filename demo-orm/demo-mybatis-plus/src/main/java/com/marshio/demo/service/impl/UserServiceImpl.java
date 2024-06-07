package com.marshio.demo.service.impl;

import com.marshio.demo.domain.entity.User;
import com.marshio.demo.mapper.UserMapper;
import com.marshio.demo.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author marshio
 * @desc ...
 * @create 2024/6/7 12:12
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.selectAll();
    }
}
