package com.dsy.hangaituangou.domain;

import com.dsy.hangaituangou.domain.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "岗位实体")
public class Job extends BaseEntity {
    @Schema(description = "职位标题", example = "Java开发工程师")
    private String jobTitle;

    @Schema(description = "工作类型", example = "全职")
    private String jobType;

    @Schema(description = "职位描述")
    private String jobDescription;

    @Schema(description = "招聘人数", example = "5")
    private Integer recruitmentCount;

    @Schema(description = "工作地点", example = "北京市海淀区")
    private String workLocation;

    @Schema(description = "薪资范围", example = "￥12,000 - ￥18,000/月")
    private String salaryRange;

    @Schema(description = "福利待遇")
    private List<String> benefits;

    @Schema(description = "学历要求", example = "本科及以上")
    private String educationRequirements;

    @Schema(description = "经验要求", example = "2年以上Java开发经验")
    private String experienceRequirements;

    @Schema(description = "技能要求")
    private List<String> skillsRequirements;

    @Schema(description = "语言要求", example = "英语六级以上")
    private String languageRequirements;

    @Schema(description = "其他要求")
    private String otherRequirements;

    @Schema(description = "职位状态", example = "开放中")
    private String jobStatus;

    @Schema(description = "发布日期")
    private Date postDate;

    @Schema(description = "截止日期")
    private Date closeDate;

    @Schema(description = "部门", example = "技术部")
    private String department;

    @Schema(description = "招聘来源", example = "在线招聘平台")
    private String recruitmentSource;

    @Schema(description = "职位标签")
    private List<String> jobTags;

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "发布者ID")
    private Long publisherId;
}