package com.dsy.hangaituangou.domain;

import com.dsy.hangaituangou.domain.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "用户角色关联实体")
public class SysUserRole extends BaseEntity {
    @Schema(description = "用户ID")
    private Long userId;      // 用户ID，关联sys_user表的id
    
    @Schema(description = "角色ID")
    private Long roleId;      // 角色ID，关联sys_role表的id
}