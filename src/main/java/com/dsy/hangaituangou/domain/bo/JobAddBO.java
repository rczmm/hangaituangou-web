package com.dsy.hangaituangou.domain.bo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "职位新增参数")
public class JobAddBO {

    @Schema(description = "职位 ID", example = "1")
    private Long id;

    @Schema(description = "职位标题", example = "Java 高级工程师")
    private String title;

    @Schema(description = "最低薪资", example = "15k")
    private String minSalary;

    @Schema(description = "最高薪资", example = "30k")
    private String maxSalary;

    @Schema(description = "薪资范围 (冗余字段，优先使用 min/max，或仅使用此字段)", example = "15k-30k")
    private String salary;

    @Schema(description = "职位标签 (JSON 格式的字符串)", example = "[\"Java\",\"SpringBoot\",\"微服务\"]")
    private List<String> tags;

    @Schema(description = "HR 用户 ID (发布职位的用户)", example = "hr_user_123")
    private String hrUserId;

    @Schema(description = "工作地点", example = "北京 海淀区")
    private String location;

    @Schema(description = "工作经验要求", example = "3-5年")
    private String workExperience;

    @Schema(description = "学历要求", example = "本科")
    private String education;

    @Schema(description = "福利待遇 (JSON 格式的字符串)", example = "[\"五险一金\",\"年底双薪\",\"带薪年假\"]")
    private List<String> benefits;

    @Schema(description = "职位描述", example = "负责核心业务系统的设计和开发...")
    private String description;

    @Schema(description = "职位要求 (JSON 格式的字符串)", example = "[\"精通Java编程\",\"熟悉SpringCloud\",\"有大型项目经验\"]")
    private List<String> requirements;

    @Schema(description = "发布日期 (如果需要前端指定)", example = "2023-10-27")
    private String date;

    @Schema(description = "职位类别", example = "全职 兼职")
    private String category;

    @Schema(description = "招聘人数", example = "1")
    private Integer count;

}
