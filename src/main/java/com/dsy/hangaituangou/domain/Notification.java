package com.dsy.hangaituangou.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dsy.hangaituangou.domain.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "通知实体")
@TableName("t_notification")
public class Notification extends BaseEntity {
    @Schema(description = "通知标题", example = "系统更新通知")
    @TableField("title")
    private String title;

    @Schema(description = "通知内容")
    @TableField("content")
    private String content;

    @Schema(description = "最后修改时间")
    @TableField("last_modified")
    private Date lastModified;

    @Schema(description = "通知颜色", example = "#ffffff")
    @TableField("color")
    private String color;

    @Schema(description = "通知类型", example = "系统通知")
    @TableField("type")
    private String type;

    @Schema(description = "通知状态：0-未读，1-已读", example = "0")
    @TableField("status")
    private Integer status;

    @Schema(description = "优先级：0-普通，1-重要，2-紧急", example = "0")
    @TableField("priority")
    private Integer priority;

    @Schema(description = "是否置顶：0-否，1-是", example = "0")
    @TableField("is_top")
    private Integer isTop;

    @Schema(description = "租户ID")
    @TableField("tenant_id")
    private Long tenantId;

    @Schema(description = "发送者ID")
    @TableField("sender_id")
    private Long senderId;

    @Schema(description = "接收者ID")
    @TableField("receiver_id")
    private Long receiverId;

    @Schema(description = "过期时间")
    @TableField("expire_time")
    private Date expireTime;
}