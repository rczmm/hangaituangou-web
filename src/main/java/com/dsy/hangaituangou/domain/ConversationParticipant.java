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
@Deprecated
public class ConversationParticipant extends BaseEntity {

    @Schema(description = "用户ID")
    private String userId;

    @Schema(description = "会话ID")
    private String conversationId;

    @Schema(description = "加入时间")
    private LocalDateTime joinedAt;

}
