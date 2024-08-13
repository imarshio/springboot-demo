package com.marshio.demo.domain.request;

import com.marshio.demo.domain.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author marshio
 * @desc ...
 * @create 2024/6/7 12:09
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class UserRequest extends User {

    List<Integer> ids;
}
