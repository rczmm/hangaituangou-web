package com.dsy.hangaituangou.domain.bo;

import com.dsy.hangaituangou.domain.base.PageBase;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "岗位查询参数")
public class JobBO extends PageBase {

    @Schema(description = "搜索关键字 岗位标题,岗位标签，公司", example = "Java开发工程师")
    private String keyword;

    @Schema(description = "类型，岗位类型", example = "品牌推广...")
    private String type;

    @Schema(description = "标签，推荐 最新 附近", example = "recommended")
    private String tag;

    @Schema(description = "职位名称", example = "高级软件工程师")
    private String title;

    @Schema(description = "最低薪资", example = "20k")
    private String minSalary;

    @Schema(description = "最高薪资", example = "35k")
    private String maxSalary;

    @Schema(description = "职位类型", example = "全职")
    private String category;

    @Schema(description = "工作地点", example = "成都")
    private String location;

}
