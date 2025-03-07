package com.dsy.hangaituangou.domain;

import com.dsy.hangaituangou.domain.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "通知实体")
public class Notification extends BaseEntity {
    @Schema(description = "通知标题", example = "系统更新通知")
    private String title;

    @Schema(description = "通知内容")
    private String content;

    @Schema(description = "最后修改时间")
    private Date lastModified;

    @Schema(description = "通知颜色", example = "#ffffff")
    private String color;

    @Schema(description = "通知类型", example = "系统通知")
    private String type;

    @Schema(description = "通知状态：0-未读，1-已读", example = "0")
    private Integer status;

    @Schema(description = "优先级：0-普通，1-重要，2-紧急", example = "0")
    private Integer priority;

    @Schema(description = "是否置顶：0-否，1-是", example = "0")
    private Integer isTop;

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "发送者ID")
    private Long senderId;

    @Schema(description = "接收者ID")
    private Long receiverId;

    @Schema(description = "过期时间")
    private Date expireTime;
}