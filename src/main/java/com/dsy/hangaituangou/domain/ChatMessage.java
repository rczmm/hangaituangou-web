package com.dsy.hangaituangou.domain;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.dsy.hangaituangou.domain.base.BaseEntity;
import com.dsy.hangaituangou.enums.MessageTypeEnum;
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
public class ChatMessage extends BaseEntity {

    private String conversationId;

    private String senderId;

    private String recipientId;

    @EnumValue
    private MessageTypeEnum messageType;

    private String content;

    private LocalDateTime sendAt;

    private String status;

}
