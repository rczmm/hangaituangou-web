package com.dsy.hangaituangou.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "会话VO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatVO {

    @Schema(description = "会话ID")
    private String id;

    @Schema(description = "会话名称")
    private String name;

    @Schema(description = "最后一条消息")
    private String lastMessage;

    @Schema(description = "头像URL")
    private String avatarUrl;

    @Schema(description = "最后一条消息时间")
    private String lastMessageTime;

    @Schema(description = "是否已读")
    private boolean isRead;

    @Schema(description = "发送者ID")
    private String senderId;

    @Schema(description = "接收者ID")
    private String recipientId;

}
