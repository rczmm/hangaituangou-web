package com.dsy.hangaituangou.domain;

import com.dsy.hangaituangou.domain.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "系统用户实体")
public class SysUser extends BaseEntity {
    @Schema(description = "用户名", example = "zhangsan")
    private String username;      // 用户名，唯一
    @Schema(description = "密码", example = "******")
    private String password;      // 密码，需要加密存储
    @Schema(description = "邮箱", example = "example@example.com")
    private String email;         // 邮箱，可用于验证和找回密码
    @Schema(description = "手机号", example = "13800138000")
    private String mobile;        // 手机号，可用于验证和找回密码
    @Schema(description = "昵称", example = "张三")
    private String nickname;      // 昵称，可选
    @Schema(description = "用户状态：0-正常，1-禁用，2-未激活", example = "0")
    private Integer status;       // 用户状态，例如：0-正常，1-禁用，2-未激活
    @Schema(description = "盐值，用于密码加密")
    private String salt;       // 盐值，用于密码加密
    @Schema(description = "头像URL", example = "http://example.com/avatar.jpg")
    private String avatar;      //头像url
    @Schema(description = "最后登录时间")
    private Date lastLoginTime;   // 最后登录时间
    @Schema(description = "最后登录IP", example = "192.168.1.1")
    private String lastLoginIp;    // 最后登录ip
    
    @Schema(description = "租户ID")
    private Long tenantId;        // 所属租户ID
    
    @Schema(description = "角色ID")
    private Long roleId;          // 用户角色ID
    
    @Schema(description = "用户类型：0-HR，1-求职者", example = "0")
    private Integer userType;     // 用户类型
}
