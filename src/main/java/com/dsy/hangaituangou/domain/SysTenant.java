package com.dsy.hangaituangou.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dsy.hangaituangou.domain.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "系统租户实体")
@TableName("sys_tenant")
public class SysTenant extends BaseEntity {
    @Schema(description = "租户名称", example = "XX公司")
    @TableField("name")
    private String name;        // 租户名称
    
    @Schema(description = "租户编码", example = "tenant_001")
    @TableField("code")
    private String code;        // 租户唯一编码
    
    @Schema(description = "租户状态：0-正常，1-禁用", example = "0")
    @TableField("status")
    private Integer status;     // 租户状态
    
    @Schema(description = "租户类型：0-企业，1-个人", example = "0")
    @TableField("type")
    private Integer type;       // 租户类型
    
    @Schema(description = "联系人", example = "张三")
    @TableField("contact_name")
    private String contactName; // 联系人姓名
    
    @Schema(description = "联系电话", example = "13800138000")
    @TableField("contact_phone")
    private String contactPhone; // 联系电话
    
    @Schema(description = "联系邮箱", example = "example@company.com")
    @TableField("contact_email")
    private String contactEmail; // 联系邮箱
    
    @Schema(description = "企业地址", example = "北京市朝阳区xx街道")
    @TableField("address")
    private String address;     // 企业地址
    
    @Schema(description = "备注信息")
    @TableField("remark")
    private String remark;      // 备注信息
}