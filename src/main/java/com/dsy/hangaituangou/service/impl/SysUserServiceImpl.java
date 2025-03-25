package com.dsy.hangaituangou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsy.hangaituangou.domain.SysUser;
import com.dsy.hangaituangou.domain.bo.LoginBo;
import com.dsy.hangaituangou.domain.security.Customer;
import com.dsy.hangaituangou.domain.vo.LoginVo;
import com.dsy.hangaituangou.domain.vo.base.RespBase;
import com.dsy.hangaituangou.exception.base.BusinessException;
import com.dsy.hangaituangou.mapper.SysUserMapper;
import com.dsy.hangaituangou.service.SysUserService;
import com.dsy.hangaituangou.utils.Constants;
import com.dsy.hangaituangou.utils.JwtUtils;
import com.google.gson.reflect.TypeToken;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final RestTemplate restTemplate;

    private final Gson gson;

    @Resource
    private PasswordEncoder passwordEncoder;


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
    public RespBase<LoginVo> wxLogin(String code) {
        // 微信登录
        String url = String.format(Constants.CODE2SESSION, Constants.APPID, Constants.SECRET, code,
                Constants.GRANT_TYPE);
        try {
            // 发送请求
            String result = restTemplate.getForObject(url, String.class);
            Map<String, String> map = gson.fromJson(result, new TypeToken<Map<String, String>>() {
            }.getType());
            if (map != null && map.containsKey("openid")) {
                String openid = map.get("openid");

                SysUser sysUser = getOne(new QueryWrapper<SysUser>().eq("username", openid));
                if (sysUser == null) {
                    LoginBo loginBo = new LoginBo();
                    loginBo.setUsername(openid);
                    loginBo.setPassword(openid);
                    loginBo.setUserType(0);
                    register(loginBo);
                }
                LoginBo loginBo = new LoginBo();
                loginBo.setUsername(openid);
                loginBo.setPassword(openid);
                return login(loginBo);
            }
        } catch (Exception e) {
            return RespBase.fail("微信登录失败");
        }
        return null;
    }
}
