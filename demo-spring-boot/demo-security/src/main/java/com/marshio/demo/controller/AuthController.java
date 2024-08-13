package com.marshio.demo.controller;

import cn.hutool.jwt.JWT;
import com.marshio.demo._const.CustomConst;
import com.marshio.demo.entity.request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

/**
 * @author marshio
 * @desc ...
 * @create 2024/8/13 14:35
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AuthController {


    private final ProviderManager providerManager;


    @PostMapping("/login")
    public String login(@RequestBody LoginRequest req) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword());
        providerManager.authenticate(authenticationToken);

        // 上一步没有抛出异常说明认证成功，我们向用户颁发jwt令牌

        return JWT.create()
                .setPayload("username", req.getUsername())
                .setKey(CustomConst.JWT_SIGN_KEY.getBytes(StandardCharsets.UTF_8))
                .sign();
    }
}
