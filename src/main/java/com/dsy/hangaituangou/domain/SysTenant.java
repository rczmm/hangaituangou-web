package com.dsy.hangaituangou.domain;

import com.dsy.hangaituangou.domain.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "系统租户实体")
public class SysTenant extends BaseEntity {
    @Schema(description = "租户名称", example = "XX公司")
    private String name;        // 租户名称
    
    @Schema(description = "租户编码", example = "tenant_001")
    private String code;        // 租户唯一编码
    
    @Schema(description = "租户状态：0-正常，1-禁用", example = "0")
    private Integer status;     // 租户状态
    
    @Schema(description = "租户类型：0-企业，1-个人", example = "0")
    private Integer type;       // 租户类型
    
    @Schema(description = "联系人", example = "张三")
    private String contactName; // 联系人姓名
    
    @Schema(description = "联系电话", example = "13800138000")
    private String contactPhone; // 联系电话
    
    @Schema(description = "联系邮箱", example = "example@company.com")
    private String contactEmail; // 联系邮箱
    
    @Schema(description = "企业地址", example = "北京市朝阳区xx街道")
    private String address;     // 企业地址
    
    @Schema(description = "备注信息")
    private String remark;      // 备注信息
}