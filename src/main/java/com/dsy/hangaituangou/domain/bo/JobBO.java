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
}
