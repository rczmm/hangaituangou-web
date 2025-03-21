package com.dsy.hangaituangou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsy.hangaituangou.domain.SysUser;
import com.dsy.hangaituangou.domain.base.RespBase;
import com.dsy.hangaituangou.domain.bo.LoginBo;
import com.dsy.hangaituangou.domain.security.Customer;
import com.dsy.hangaituangou.domain.vo.LoginVo;
import com.dsy.hangaituangou.exception.base.BusinessException;
import com.dsy.hangaituangou.mapper.SysUserMapper;
import com.dsy.hangaituangou.service.SysUserService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;


    @Override
    public RespBase<LoginVo> login(LoginBo loginBo) {
        userDetailsService.loadUserByUsername(loginBo.getUsername());
        // 数据封装
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginBo.getUsername(), loginBo.getPassword());
        // 调用loadUserByUsername
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (Exception ex) {
            return RespBase.error("用户名或密码错误");
        }
        Customer customer = (Customer) authentication.getPrincipal();
        SysUser sysUser = customer.getSysUser();
        
        // 构建LoginVo对象
        LoginVo loginVo = new LoginVo();
        loginVo.setUserId(sysUser.getId());
        loginVo.setUsername(sysUser.getUsername());
        loginVo.setNickname(sysUser.getNickname());
        // TODO: 这里需要集成JWT等token生成逻辑
        loginVo.setToken("JWT_TOKEN_" + sysUser.getUsername());
        
        return RespBase.success(loginVo);
    }

    @Override
    public Boolean logout() {
        return true;
    }

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public String register(LoginBo loginBo) {
        try {
            userDetailsService.loadUserByUsername(loginBo.getUsername());
            throw new BusinessException("用户名已存在");
        } catch (BusinessException businessException) {
            if ("用户名不存在！".equals(businessException.getMessage())) {
                SysUser sysUser = new SysUser();
                sysUser.setUsername(loginBo.getUsername());
                sysUser.setPassword(passwordEncoder.encode(loginBo.getPassword()));
                boolean save = save(sysUser);
                if (save) {
                    return "注册成功";
                } else {
                    throw new BusinessException("注册失败，请稍后重试");
                }
            }
            throw businessException;
        }
    }
}
