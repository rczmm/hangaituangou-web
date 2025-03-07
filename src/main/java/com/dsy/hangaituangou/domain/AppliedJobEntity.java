package com.dsy.hangaituangou.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dsy.hangaituangou.domain.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "应聘岗位信息实体")
@TableName("t_applied_job")
public class AppliedJobEntity extends BaseEntity {
    @Schema(description = "候选人ID", example = "C001")
    @TableField("candidate_id")
    private String candidateId;
    
    @Schema(description = "岗位标题", example = "Java开发工程师")
    @TableField("job_title")
    private String jobTitle;
    
    @Schema(description = "岗位ID", example = "J001")
    @TableField("job_id")
    private String jobId;
    
    @Schema(description = "申请日期", example = "2025-02-10")
    @TableField("apply_date")
    private Date applyDate;
    
    @Schema(description = "简历ID", example = "R001")
    @TableField("resume_id")
    private String resumeId;
    
    // 移除了租户ID字段
}