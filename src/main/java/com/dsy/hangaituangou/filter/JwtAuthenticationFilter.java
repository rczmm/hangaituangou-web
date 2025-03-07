package com.dsy.hangaituangou.filter;

import com.dsy.hangaituangou.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Component
@RequiredArgsConstructor
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
        // 如果header中没有token，尝试从请求参数中获取
        if (token == null) {
            token = request.getParameter("token");
        }

        // 如果没有token，拒绝访问
        if (token == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("未授权：缺少有效的认证令牌");
            return;
        }

        // 如果token以Bearer 开头，去掉这个前缀
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        try {
            // 验证token
            if (!JwtUtils.validateToken(token)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("未授权：无效的认证令牌");
                return;
            }

            // 检查token是否过期
            if (JwtUtils.isTokenExpired(token)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("未授权：认证令牌已过期");
                return;
            }

            // 从token中获取用户名
            String username = JwtUtils.getUsernameFromToken(token);

            // 创建认证对象并设置到SecurityContext
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                    );

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("未授权：" + e.getMessage());
        }
    }
}
