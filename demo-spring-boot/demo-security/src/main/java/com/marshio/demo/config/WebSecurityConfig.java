package com.marshio.demo.config;

import com.marshio.demo.filter.JwtAuthenticationTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author marshio
 * @desc ...
 * @create 2024/8/13 14:52
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationTokenFilter authenticationTokenFilter;

    @Bean
    public AuthenticationManager authenticationManager()
            throws Exception {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        // daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return new ProviderManager(daoAuthenticationProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // 基于token，所以不需要csrf防护
        httpSecurity.csrf().disable()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 登录注册不需要认证
                .antMatchers("/user/login", "/user/register").permitAll()
                // 除上面的所有请求全部需要鉴权认证
                .anyRequest()
                .authenticated();
        // 禁用缓存
        httpSecurity.headers().cacheControl();
        // 将我们的JWT filter添加到UsernamePasswordAuthenticationFilter前面，因为这个Filter是authentication开始的filter，我们要早于它
        httpSecurity.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
