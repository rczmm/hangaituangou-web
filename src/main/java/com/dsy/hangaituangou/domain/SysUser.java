package com.dsy.hangaituangou.domain;

import com.dsy.hangaituangou.domain.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysUser extends BaseEntity {
    private String username;      // 用户名，唯一
    private String password;      // 密码，需要加密存储
    private String email;         // 邮箱，可用于验证和找回密码
    private String mobile;        // 手机号，可用于验证和找回密码
    private String nickname;      // 昵称，可选
    private Integer status;       // 用户状态，例如：0-正常，1-禁用，2-未激活
    private String salt;       // 盐值，用于密码加密
    private String avatar;      //头像url
    private Date lastLoginTime;   // 最后登录时间
    private String lastLoginIp;    // 最后登录ip
}
