package com.dsy.hangaituangou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsy.hangaituangou.domain.SysUser;
import com.dsy.hangaituangou.mapper.sysUserMapper;
import com.dsy.hangaituangou.service.sysUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<sysUserMapper, SysUser> implements sysUserService {
}
