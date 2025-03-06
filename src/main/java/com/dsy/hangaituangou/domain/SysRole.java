package com.dsy.hangaituangou.domain;

import com.dsy.hangaituangou.domain.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "系统角色实体")
public class SysRole extends BaseEntity {
    @Schema(description = "角色名称", example = "管理员")
    private String roleName;      // 角色名称
    
    @Schema(description = "角色编码", example = "ADMIN")
    private String roleCode;      // 角色编码，唯一
    
    @Schema(description = "角色描述", example = "系统管理员角色")
    private String description;   // 角色描述
}