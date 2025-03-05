package com.dsy.hangaituangou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsy.hangaituangou.domain.SysUser;
import com.dsy.hangaituangou.domain.bo.LoginBo;
import com.dsy.hangaituangou.domain.security.Customer;
import com.dsy.hangaituangou.exception.base.BusinessException;
import com.dsy.hangaituangou.mapper.SysUserMapper;
import com.dsy.hangaituangou.service.SysUserService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;


    @Override
    public String login(LoginBo loginBo) {
        userDetailsService.loadUserByUsername(loginBo.getUsername());
        // 数据封装
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginBo.getUsername(), loginBo.getPassword());
        // 调用loadUserByUsername
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (Exception ex) {
            throw new BusinessException("用户名或密码错误");
        }
        Customer customer = (Customer) authentication.getPrincipal();
        SysUser sysUser = customer.getSysUser();
        return "1111111111111111111111111" + sysUser.getUsername() + sysUser.getPassword();
    }
}
