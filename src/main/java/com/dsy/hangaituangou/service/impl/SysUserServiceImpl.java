package com.dsy.hangaituangou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsy.hangaituangou.domain.SysUser;
import com.dsy.hangaituangou.domain.bo.LoginBo;
import com.dsy.hangaituangou.domain.security.Customer;
import com.dsy.hangaituangou.domain.vo.LoginVo;
import com.dsy.hangaituangou.domain.vo.base.RespBase;
import com.dsy.hangaituangou.exception.base.BusinessException;
import com.dsy.hangaituangou.mapper.SysUserMapper;
import com.dsy.hangaituangou.service.SysUserService;
import com.dsy.hangaituangou.utils.JwtUtils;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;


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
            return RespBase.fail("用户名或密码错误");
        }
        Customer customer = (Customer) authentication.getPrincipal();
        SysUser sysUser = customer.getSysUser();

        // 构建LoginVo对象
        LoginVo loginVo = new LoginVo();
        loginVo.setUserId(sysUser.getId());
        loginVo.setUsername(sysUser.getUsername());
        loginVo.setNickname(sysUser.getNickname());
        loginVo.setAvatar(sysUser.getAvatar()); // 添加头像信息

        // 使用JwtUtils生成加密的token
        String token = JwtUtils.generateToken(sysUser, loginBo.getPassword());
        loginVo.setToken(token);

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
                // 设置用户类型（HR或求职者）
                sysUser.setUserType(loginBo.getUserType());
                // 根据用户类型设置对应的角色ID
                // TODO: 这里需要从数据库中获取对应的角色ID
                sysUser.setRoleType(loginBo.getUserType() == 0 ? 1L : 2L); // 假设1是HR角色，2是求职者角色
                // 写入邮箱
                sysUser.setEmail(loginBo.getEmail());
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

    @Override
    public String updatePassword(LoginBo loginBo) {
        try {
            list().stream().filter(sysUser -> sysUser.getUsername().equals(loginBo.getUsername())).findFirst().ifPresent(sysUser -> {
                sysUser.setPassword(passwordEncoder.encode(loginBo.getPassword()));
                updateById(sysUser);
            });
        } catch (Exception e) {
            throw new BusinessException("修改密码失败");
        }
        return "修改成功！";
    }

    @Override
    public String getNameById(String userId) {
        return getById(userId).getNickname();
    }
}
