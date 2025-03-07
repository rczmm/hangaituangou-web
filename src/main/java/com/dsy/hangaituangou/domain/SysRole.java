package com.dsy.hangaituangou.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dsy.hangaituangou.domain.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "系统角色实体")
@TableName("sys_role")
public class SysRole extends BaseEntity {
    @Schema(description = "角色名称", example = "HR")
    @TableField("name")
    private String name;        // 角色名称
    
    @Schema(description = "角色编码", example = "hr")
    @TableField("code")
    private String code;        // 角色编码，唯一
    
    @Schema(description = "角色状态：0-正常，1-禁用", example = "0")
    @TableField("status")
    private Integer status;     // 角色状态
    
    @Schema(description = "角色描述")
    @TableField("description")
    private String description; // 角色描述
    
    @Schema(description = "租户ID")
    @TableField("tenant_id")
    private Long tenantId;      // 所属租户ID
    
    @Schema(description = "是否系统内置角色：0-否，1-是", example = "0")
    @TableField("is_system")
    private Integer isSystem;   // 是否系统内置角色
    
    @Schema(description = "排序号", example = "1")
    @TableField("sort")
    private Integer sort;       // 排序号
}