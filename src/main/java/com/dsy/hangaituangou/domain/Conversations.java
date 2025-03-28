package com.dsy.hangaituangou.domain;

import com.dsy.hangaituangou.domain.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Conversations extends BaseEntity {

    @Schema(description = "会话类型")
    private String conversationType;

    @Schema(description = "会话ID")
    private String name;

    @Schema(description = "最后一条消息时间")
    private LocalDateTime lastMessageAt;

    @Schema(description = "最后一条消息")
    private String lastMessage;

    @Schema(description = "发送者ID")
    private String senderId;

    @Schema(description = "接收者ID")
    private String recipientId;


}
