package com.dsy.hangaituangou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dsy.hangaituangou.domain.SysRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    // 基础的CRUD操作由MyBatis-Plus提供
}