package com.marshio.demo.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author marshio
 * @desc ...
 * @create 2024/8/13 16:14
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    // private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        // 校验
        // LoginUser loginUser = tokenService.getLoginUser(request);
        // if (StringUtils.isNotNull(loginUser) && StringUtils.isNull(SecurityUtils.getAuthentication())) {
        //     tokenService.verifyToken(loginUser);
        //     UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        //     authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        //     SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // }
        chain.doFilter(request, response);
    }
}
