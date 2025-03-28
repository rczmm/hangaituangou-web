package com.dsy.hangaituangou.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageVO {

    private String id;
    private String senderId;
    private String senderName;
    private String text;
    private String timestamp;
    private boolean isMe;
    private String avatarUrl;
    private boolean isFile;
    private String fileName;
    private String fileUrl;

}
