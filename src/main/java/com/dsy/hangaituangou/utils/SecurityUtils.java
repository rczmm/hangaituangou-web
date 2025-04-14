package com.dsy.hangaituangou.utils;

import com.dsy.hangaituangou.domain.SysUser;
import com.dsy.hangaituangou.domain.security.Customer;
import com.dsy.hangaituangou.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * 安全工具类，用于获取当前登录用户信息
 */
public class SecurityUtils {


    /**
     * 获取当前登录用户
     *
     * @return 当前登录用户
     */
    public static SysUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        // 此处是获取用户id的
        Object principal = authentication.getPrincipal();
        if (principal instanceof Customer customer) {
            return customer.getSysUser();
        } else if (principal instanceof Long userId) {
            return new SysUser() {{
                setId(userId);
            }};
        }
        return null;
    }

    /**
     * 获取当前登录用户的ID
     *
     * @return 用户ID
     */
    public static Long getCurrentUserId() {
        SysUser user = getCurrentUser();
        return user != null ? user.getId() : null;
    }
}