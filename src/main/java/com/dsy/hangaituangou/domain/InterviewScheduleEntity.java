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
@Schema(description = "面试安排信息实体")
@TableName("t_interview_schedule")
public class InterviewScheduleEntity extends BaseEntity {
    @Schema(description = "候选人ID", example = "C001")
    @TableField("candidate_id")
    private String candidateId;
    
    @Schema(description = "面试ID")
    @TableField("interview_id")
    private String interviewId;
    
    @Schema(description = "面试日期时间")
    @TableField("interview_date_time")
    private Date interviewDateTime;
    
    @Schema(description = "面试地点")
    @TableField("interview_location")
    private String interviewLocation;
    
    @Schema(description = "面试官ID")
    @TableField("interviewer_id")
    private String interviewerId;
    
    @Schema(description = "面试官姓名")
    @TableField("interviewer_name")
    private String interviewerName;
    
    @Schema(description = "面试类型", example = "技术面试")
    @TableField("interview_type")
    private String interviewType;
    
    // 移除了租户ID字段
}