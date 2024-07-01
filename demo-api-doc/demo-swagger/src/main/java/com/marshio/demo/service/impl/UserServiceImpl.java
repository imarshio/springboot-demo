package com.marshio.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.marshio.demo.domain.entity.User;
import com.marshio.demo.domain.rest.PageRequest;
import com.marshio.demo.domain.request.UserRequest;
import com.marshio.demo.mapper.UserMapper;
import com.marshio.demo.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



/**
 * @author marshio
 * @desc ...
 * @create 2024/6/7 12:12
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User getUserById(Integer id) {
        return this.getById(id);
    }

    @Override
    public Page<User> getUsers(PageRequest<UserRequest> request) {

        // page.getTotal()
        Page<User> page = new Page<>(request.getPageNo(), request.getPageSize());

        if (null != request.getTotal()) {
            // 如已存在总数，则无需查询总数，减轻数据库压力
            // 此总数可以通过其他方式获取，如存在缓存，则直接从缓存中获取
            // 注：需要考虑具体的场景，什么时候前端可以传总数，什么情况不可以传
            page.setTotal(request.getTotal());
            page.setSearchCount(false);
        }
        page.setOrders(request.getOrders());

        // 构建查询条件
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        if (null != request.getQuery()) {
            query.eq(null != request.getQuery().getId(), User::getId, request.getQuery().getId())
                    .eq(null != request.getQuery().getUsername(), User::getUsername, request.getQuery().getUsername())
                    .in(null != request.getQuery().getIds(), User::getId, request.getQuery().getIds());
        }

        // 使用mybatis-plus自带的分页查询
        return this.getBaseMapper().selectPage(page, query);
    }

    @Override
    public Boolean addUser(User entity) {
        return this.save(entity);
    }
}
