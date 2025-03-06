package com.dsy.hangaituangou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dsy.hangaituangou.domain.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    // 基础的CRUD操作由MyBatis-Plus提供
}