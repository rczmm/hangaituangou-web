package com.dsy.hangaituangou.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dsy.hangaituangou.domain.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "候选人实体")
@TableName("t_candidate")
public class CandidateEntity extends BaseEntity {
    @Schema(description = "候选人ID", example = "C001")
    @TableField("candidate_id")
    private String candidateId;
    
    @Schema(description = "候选人姓名", example = "张三")
    @TableField("candidate_name")
    private String candidateName;
    
    @Schema(description = "候选人电话", example = "13812345678")
    @TableField("candidate_phone")
    private String candidatePhone;
    
    @Schema(description = "候选人邮箱", example = "zhangsan@example.com")
    @TableField("candidate_email")
    private String candidateEmail;
    
    @Schema(description = "候选人性别", example = "男")
    @TableField("candidate_gender")
    private String candidateGender;
    
    @Schema(description = "候选人出生日期", example = "1990-01-01")
    @TableField("candidate_dob")
    private Date candidateDob;
    
    @Schema(description = "候选人地址", example = "北京市海淀区XX街道")
    @TableField("candidate_address")
    private String candidateAddress;
    
    @Schema(description = "候选人国籍", example = "中国")
    @TableField("candidate_nationality")
    private String candidateNationality;
    
    @Schema(description = "候选人学历", example = "本科")
    @TableField("candidate_education")
    private String candidateEducation;
    
    @Schema(description = "候选人专业", example = "计算机科学与技术")
    @TableField("candidate_major")
    private String candidateMajor;
    
    @Schema(description = "工作经验", example = "3年")
    @TableField("candidate_experience")
    private String candidateExperience;
    
    @Schema(description = "技能列表")
    @TableField("candidate_skills")
    private List<String> candidateSkills;
    
    @Schema(description = "面试状态", example = "待安排面试")
    @TableField("interview_status")
    private String interviewStatus;
    
    @Schema(description = "面试反馈")
    @TableField("interview_feedback")
    private String interviewFeedback;
    
    @Schema(description = "招聘状态", example = "待定")
    @TableField("hiring_status")
    private String hiringStatus;
    
    @Schema(description = "备注")
    @TableField("remarks")
    private String remarks;
    
    // 移除了租户ID字段
}