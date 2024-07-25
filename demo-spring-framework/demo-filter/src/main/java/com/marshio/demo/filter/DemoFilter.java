package com.marshio.demo.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author marshio
 * @desc ...
 * @create 2024/7/25 16:50
 */

@Slf4j
@Component
@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        // Filter 由Servlet规范规定，在Servlet前执行，用于拦截和处理 HTTP 请求和响应，可用于身份认证、授权、日志记录和设置字符集（CharacterEncodingFilter）等场景
        //
        // 过滤器位于整个请求处理流程的最前端，因此在请求到达 Controller 层前，都会先被过滤器处理。
        //
        // 过滤器可以拦截多个请求或响应，一个请求或响应也可以被多个过滤器拦截。
        log.info("DemoFilter do filter");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        log.info("DemoFilter init");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        log.info("DemoFilter destroy");
    }
}
