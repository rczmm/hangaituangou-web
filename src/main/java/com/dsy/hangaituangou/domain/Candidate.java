package com.dsy.hangaituangou.domain;

import com.dsy.hangaituangou.domain.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "候选人实体")
public class Candidate extends BaseEntity {
    @Schema(description = "候选人ID", example = "C001")
    private String candidateId;
    
    @Schema(description = "候选人姓名", example = "张三")
    private String candidateName;
    
    @Schema(description = "候选人电话", example = "13812345678")
    private String candidatePhone;
    
    @Schema(description = "候选人邮箱", example = "zhangsan@example.com")
    private String candidateEmail;
    
    @Schema(description = "候选人性别", example = "男")
    private String candidateGender;
    
    @Schema(description = "候选人出生日期", example = "1990-01-01")
    private Date candidateDob;
    
    @Schema(description = "候选人地址", example = "北京市海淀区XX街道")
    private String candidateAddress;
    
    @Schema(description = "候选人国籍", example = "中国")
    private String candidateNationality;
    
    @Schema(description = "候选人学历", example = "本科")
    private String candidateEducation;
    
    @Schema(description = "候选人专业", example = "计算机科学与技术")
    private String candidateMajor;
    
    @Schema(description = "工作经验", example = "3年")
    private String candidateExperience;
    
    @Schema(description = "技能列表")
    private List<String> candidateSkills;
    
    @Schema(description = "应聘岗位列表")
    private List<AppliedJob> appliedJobs;
    
    @Schema(description = "简历文件信息")
    private ResumeFile resumeFile;
    
    @Schema(description = "面试状态", example = "待安排面试")
    private String interviewStatus;
    
    @Schema(description = "面试安排")
    private InterviewSchedule interviewSchedule;
    
    @Schema(description = "面试反馈")
    private String interviewFeedback;
    
    @Schema(description = "招聘状态", example = "待定")
    private String hiringStatus;
    
    @Schema(description = "备注")
    private String remarks;
    
    @Schema(description = "租户ID")
    private Long tenantId;
    
    @Data
    @Schema(description = "应聘岗位信息")
    public static class AppliedJob {
        @Schema(description = "岗位标题", example = "Java开发工程师")
        private String jobTitle;
        
        @Schema(description = "岗位ID", example = "J001")
        private String jobId;
        
        @Schema(description = "申请日期", example = "2025-02-10")
        private Date applyDate;
        
        @Schema(description = "简历ID", example = "R001")
        private String resumeId;
    }
    
    @Data
    @Schema(description = "简历文件信息")
    public static class ResumeFile {
        @Schema(description = "文件名", example = "张三_Java开发工程师_简历.pdf")
        private String fileName;
        
        @Schema(description = "文件类型", example = "application/pdf")
        private String fileType;
        
        @Schema(description = "文件大小(字节)", example = "512000")
        private Long fileSize;
        
        @Schema(description = "文件路径", example = "/resumes/张三_Java开发工程师_简历.pdf")
        private String filePath;
        
        @Schema(description = "上传日期", example = "2025-02-10")
        private Date uploadDate;
    }
    
    @Data
    @Schema(description = "面试安排信息")
    public static class InterviewSchedule {
        @Schema(description = "面试ID")
        private String interviewId;
        
        @Schema(description = "面试日期时间")
        private Date interviewDateTime;
        
        @Schema(description = "面试地点")
        private String interviewLocation;
        
        @Schema(description = "面试官ID")
        private String interviewerId;
        
        @Schema(description = "面试官姓名")
        private String interviewerName;
        
        @Schema(description = "面试类型", example = "技术面试")
        private String interviewType;
    }
}