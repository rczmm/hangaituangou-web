package com.dsy.hangaituangou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dsy.hangaituangou.domain.SysUser;
import com.dsy.hangaituangou.domain.security.Customer;
import com.dsy.hangaituangou.exception.base.BusinessException;
import com.dsy.hangaituangou.service.sysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final sysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserService.getOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, username).last("limit 1"));

        if (user == null) {
            throw new BusinessException("用户名不存在！");
        }

        if (user.getStatus() == 1) {
            throw new BusinessException("你的账号已被停用！");
        }

        return new Customer(user, Collections.emptyList());
    }
}
