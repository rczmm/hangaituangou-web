package com.dsy.hangaituangou.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // 定义不需要token验证的路径
    private static final List<String> PERMIT_URLS = Arrays.asList(
            "/auth/login",
            "/auth/register",
            "/auth/logout",
            "/"
    );

    private boolean shouldPermit(String requestURI) {
        return PERMIT_URLS.stream()
                .anyMatch(requestURI::startsWith);
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        // 检查是否是无需验证的路径
        String requestURI = request.getRequestURI();
        if (shouldPermit(requestURI)) {
            filterChain.doFilter(request, response);
            return;
        }
        // 获取token
        String token = request.getHeader("Authorization");
        System.out.println("Filter token:" + token);
        // login、register请求无token，直接放行
        if (token == null) {
            doFilter(request, response, filterChain);
            return;
        }
        // TODO: 2025/1/14 存在token 解析token 
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("dsy", null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        System.out.println("authenticationToken: " + authenticationToken);
        doFilter(request, response, filterChain);
    }
}
