package com.dsy.hangaituangou.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Schema(description = "岗位列表响应数据")
public class JobVO {

    @Schema(description = "岗位ID", example = "J001")
    private String id;

    @Schema(description = "岗位名称", example = "高级软件工程师")
    private String title;

    @Schema(description = "薪资范围", example = "20k-35k")
    private String salary;

    @Schema(description = "公司名称", example = "ABC科技有限公司")
    private String company;

    @Schema(description = "公司规模", example = "500-1000人")
    private String companySize;

    @Schema(description = "公司Logo", example = "https://example.com/logo_abc.png")
    private String companyLogo;

    @Schema(description = "岗位标签", example = "[\"Java\", \"Spring Boot\", \"MySQL\"]")
    private List<String> tags;

    @Schema(description = "HR 姓名", example = "张三")
    private String hrName;

    @Schema(description = "工作地点", example = "上海")
    private String location;

    @Schema(description = "工作经验要求", example = "3-5年")
    private String workExperience;

    @Schema(description = "学历要求", example = "本科")
    private String education;

    @Schema(description = "福利待遇", example = "[\"五险一金\", \"带薪年假\", \"绩效奖金\"]")
    private List<String> benefits;

    @Schema(description = "岗位描述", example = "负责核心模块的开发和维护。")
    private String description;

    @Schema(description = "岗位要求", example = "[\"精通 Java 编程\", \"熟悉 Spring Boot 框架\"]")
    private List<String> requirements;

    @Schema(description = "招聘状态", example = "招聘中")
    private String status;

    @Schema(description = "发布日期", example = "2025-03-20")
    private String date;

    @Schema(description = "面试时间", example = "2025-03-25 10:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime interviewTime;

    @Schema(description = "是否收藏", example = "false")
    private Boolean isFavorite;

}
