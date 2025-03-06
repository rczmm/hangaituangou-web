package com.dsy.hangaituangou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dsy.hangaituangou.domain.SysUser;
import com.dsy.hangaituangou.domain.base.RespBase;
import com.dsy.hangaituangou.domain.bo.LoginBo;
import com.dsy.hangaituangou.domain.vo.LoginVo;


public interface SysUserService extends IService<SysUser> {

    RespBase<LoginVo> login(LoginBo loginBo);

    Boolean logout();

    String register(LoginBo loginBo);

}
