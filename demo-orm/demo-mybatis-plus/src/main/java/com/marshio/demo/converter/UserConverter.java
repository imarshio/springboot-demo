package com.marshio.demo.converter;

import com.marshio.demo.domain.entity.User;
import com.marshio.demo.domain.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author marshio
 * @desc ...
 * @create 2024/6/7 12:13
 */
@Mapper
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    UserVO toVO(User user);

    List<UserVO> toVO(List<User> user);
}
