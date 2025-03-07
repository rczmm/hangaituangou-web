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
@Schema(description = "岗位实体")
@TableName("t_job")
public class Job extends BaseEntity {
    @Schema(description = "职位标题", example = "Java开发工程师")
    @TableField("job_title")
    private String jobTitle;

    @Schema(description = "工作类型", example = "全职")
    @TableField("job_type")
    private String jobType;

    @Schema(description = "职位描述")
    @TableField("job_description")
    private String jobDescription;

    @Schema(description = "招聘人数", example = "5")
    @TableField("recruitment_count")
    private Integer recruitmentCount;

    @Schema(description = "工作地点", example = "北京市海淀区")
    @TableField("work_location")
    private String workLocation;

    @Schema(description = "薪资范围", example = "￥12,000 - ￥18,000/月")
    @TableField("salary_range")
    private String salaryRange;

    @Schema(description = "福利待遇")
    @TableField("benefits")
    private List<String> benefits;

    @Schema(description = "学历要求", example = "本科及以上")
    @TableField("education_requirements")
    private String educationRequirements;

    @Schema(description = "经验要求", example = "2年以上Java开发经验")
    @TableField("experience_requirements")
    private String experienceRequirements;

    @Schema(description = "技能要求")
    @TableField("skills_requirements")
    private List<String> skillsRequirements;

    @Schema(description = "语言要求", example = "英语六级以上")
    @TableField("language_requirements")
    private String languageRequirements;

    @Schema(description = "其他要求")
    @TableField("other_requirements")
    private String otherRequirements;

    @Schema(description = "职位状态", example = "开放中")
    @TableField("job_status")
    private String jobStatus;

    @Schema(description = "发布日期")
    @TableField("post_date")
    private Date postDate;

    @Schema(description = "截止日期")
    @TableField("close_date")
    private Date closeDate;

    @Schema(description = "部门", example = "技术部")
    @TableField("department")
    private String department;

    @Schema(description = "招聘来源", example = "在线招聘平台")
    @TableField("recruitment_source")
    private String recruitmentSource;

    @Schema(description = "职位标签")
    @TableField("job_tags")
    private List<String> jobTags;

    // 移除了租户ID字段

    @Schema(description = "发布者ID")
    @TableField("publisher_id")
    private Long publisherId;
}