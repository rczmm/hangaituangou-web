package com.dsy.hangaituangou.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.dsy.hangaituangou.domain.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 职位实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("job")
@Schema(description = "职位信息")
public class Job extends BaseEntity {

    /**
     * final String title;
     */
    @TableField("title")
    @Schema(description = "职位标题")
    private String title;

    /**
     * 最低薪资
     */
    @TableField("min_salary")
    @Schema(description = "最低薪资")
    private String minSalary;

    /**
     * 最高薪资
     */
    @TableField("max_salary")
    @Schema(description = "最高薪资")
    private String maxSalary;

    /**
     * final String salary;
     */
    @TableField("salary")
    @Schema(description = "薪资范围")
    private String salary;

    /**
     * final String company;
     */
    @TableField("company")
    @Schema(description = "公司名称")
    private String company;

    /**
     * final String companySize;
     */
    @TableField("company_size")
    @Schema(description = "公司规模")
    private String companySize;

    /**
     * final String companyLogo;
     */
    @TableField("company_logo")
    @Schema(description = "公司Logo")
    private String companyLogo;

    /**
     * final List<String> tags; (存储为 JSON 字符串)
     */
    @TableField("tags")
    @Schema(description = "职位标签 (JSON 格式)")
    private String tags;

    /**
     * final String hrName;
     */
    @TableField("hr_name")
    @Schema(description = "HR 姓名")
    private String hrName;

    /**
     * final String location;
     */
    @TableField("location")
    @Schema(description = "工作地点")
    private String location;

    /**
     * final String workExperience;
     */
    @TableField("work_experience")
    @Schema(description = "工作经验要求")
    private String workExperience;

    /**
     * final String education;
     */
    @TableField("education")
    @Schema(description = "学历要求")
    private String education;

    /**
     * final List<String> benefits; (存储为 JSON 字符串)
     */
    @TableField("benefits")
    @Schema(description = "福利待遇 (JSON 格式)")
    private String benefits;

    /**
     * final String description;
     */
    @TableField("description")
    @Schema(description = "职位描述")
    private String description;

    /**
     * final List<String> requirements; (存储为 JSON 字符串)
     */
    @TableField("requirements")
    @Schema(description = "职位要求 (JSON 格式)")
    private String requirements;

    /**
     * final String status;
     */
    @TableField("status")
    @Schema(description = "职位状态")
    private String status;

    /**
     * final String date;
     */
    @TableField("date")
    @Schema(description = "发布日期")
    private String date;

    /**
     * final DateTime? interviewTime;
     */
    @TableField("interview_time")
    @Schema(description = "面试时间")
    private LocalDateTime interviewTime;

    /**
     * final bool isFavorite;
     */
    @TableField("is_favorite")
    @Schema(description = "是否收藏")
    private Boolean isFavorite;

}