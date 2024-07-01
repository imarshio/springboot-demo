package com.marshio.demo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
@DS("master")
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public List<User> getAllUser() {
        return this.list();
    }

}
