package com.dsy.hangaituangou.domain.bo;

import com.dsy.hangaituangou.domain.base.PageBase;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "岗位查询参数")
public class JobBO extends PageBase {

    @Schema(description = "岗位标题", example = "Java开发工程师")
    private String title;

    @Schema(description = "公司名称", example = "字节跳动")
    private String company;

    @Schema(description = "技能标签", example = "Java")
    private String tag;
}
