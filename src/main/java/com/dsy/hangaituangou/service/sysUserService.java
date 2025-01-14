package com.dsy.hangaituangou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dsy.hangaituangou.domain.SysUser;
import com.dsy.hangaituangou.domain.bo.LoginBo;

import java.util.Map;

public interface sysUserService extends IService<SysUser> {

    Map<String,String> login(LoginBo loginBo);

}
