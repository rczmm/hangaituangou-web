package com.dsy.hangaituangou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dsy.hangaituangou.domain.SysUser;
import com.dsy.hangaituangou.domain.bo.LoginBo;


public interface SysUserService extends IService<SysUser> {

    String login(LoginBo loginBo);

    Boolean logout();
}
