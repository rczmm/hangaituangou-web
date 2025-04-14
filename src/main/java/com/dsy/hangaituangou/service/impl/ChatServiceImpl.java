package com.dsy.hangaituangou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dsy.hangaituangou.domain.ChatMessage;
import com.dsy.hangaituangou.domain.Conversations;
import com.dsy.hangaituangou.domain.Interview;
import com.dsy.hangaituangou.domain.SysUser;
import com.dsy.hangaituangou.domain.bo.ChatBO;
import com.dsy.hangaituangou.domain.vo.ChatVO;
import com.dsy.hangaituangou.domain.vo.MessageVO;
import com.dsy.hangaituangou.enums.InterviewStatusEnum;
import com.dsy.hangaituangou.enums.MessageTypeEnum;
import com.dsy.hangaituangou.exception.base.BusinessException;
import com.dsy.hangaituangou.service.*;
import com.dsy.hangaituangou.utils.SecurityUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ConversationService conversationService;

    private final SysUserService sysUserService;

    private final ChatMessageService chatMessageService;

    private final InterviewService interviewService;

    private final Gson gson = new Gson();


    @Override
    public List<ChatVO> list(String userId) {

        if (!StringUtils.hasText(userId)) {
            throw new BusinessException("用户ID不能为空");
        }

        List<Conversations> conversationsList = conversationService.list(new LambdaQueryWrapper<Conversations>()
                .eq(Conversations::getSenderId, userId)
                .or()
                .eq(Conversations::getRecipientId, userId)
                .orderByDesc(Conversations::getLastMessageAt)
                .last("limit 20"));

        List<SysUser> sysUserList = conversationsList.isEmpty() ? null : sysUserService.list(new LambdaQueryWrapper<SysUser>()
                .in(SysUser::getId, conversationsList.stream()
                        .map(conversations -> conversations.getSenderId().equals(userId) ? conversations.getRecipientId() : conversations.getSenderId())
                        .toList()));


        return conversationsList.stream()
                .map(conversations -> ChatVO.builder()
                        .id(conversations.getId().toString())
                        .name(sysUserList.stream()
                                .filter(sysUser -> sysUser.getId().toString().equals(conversations.getSenderId().equals(userId) ? conversations.getRecipientId() : conversations.getSenderId()))
                                .findFirst()
                                .map(SysUser::getNickname)
                                .orElse("未知用户"))
                        .avatarUrl(sysUserList.stream()
                                .filter(sysUser -> sysUser.getId().toString().equals(conversations.getSenderId().equals(userId) ? conversations.getRecipientId() : conversations.getSenderId()))
                                .findFirst()
                                .map(SysUser::getAvatar)
                                .orElse(""))
                        .lastMessage(conversations.getLastMessage())
                        .lastMessageTime(Optional.ofNullable(conversations.getLastMessageAt())
                                .map(object -> object.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                .orElse(null))
                        .isRead(true)
                        .senderId(conversations.getSenderId())
                        .recipientId(conversations.getRecipientId())
                        .build())
                .toList();


    }

    @Override
    public Boolean create(ChatBO chatBO) {
        try {
            return conversationService.save(Conversations.builder()
                    .conversationType("private")
                    .name(chatBO.getReceiverId())
                    .senderId(chatBO.getSenderId())
                    .recipientId(chatBO.getReceiverId())
                    .build());
        } catch (DuplicateKeyException e) {
            return false;
        }
    }

    @Override
    public Boolean saveMessage(String senderId, String recipientId, String text) {
        Conversations conversations = conversationService.list(
                new LambdaQueryWrapper<Conversations>()
                        .eq(Conversations::getSenderId, senderId)
                        .eq(Conversations::getRecipientId, recipientId)
                        .or()
                        .eq(Conversations::getSenderId, recipientId)
                        .eq(Conversations::getRecipientId, senderId)
                        .last("limit 1")).stream().findFirst().orElseThrow(() -> new BusinessException("会话不存在"));

        ChatMessage chatMessage = ChatMessage.builder()
                .conversationId(conversations.getId().toString())
                .senderId(senderId)
                .recipientId(recipientId)
                .messageType(text.startsWith("{") ? MessageTypeEnum.INTERVIEW_INVITATION : MessageTypeEnum.TEXT)
                .content(text)
                .status("sent")
                .sendAt(LocalDateTime.now())
                .build();
        if (chatMessageService.save(chatMessage)) {
            conversations.setLastMessage(text);
            conversations.setLastMessageAt(chatMessage.getSendAt());

            if (chatMessage.getMessageType().equals(MessageTypeEnum.INTERVIEW_INVITATION)) {
                conversations.setLastMessage(MessageTypeEnum.INTERVIEW_INVITATION.getDescription());
                Map<String, String> fromJson = gson.fromJson(text, new TypeToken<Map<String, String>>() {
                }.getType());
                Interview interview = new Interview();
                interview.setUserId(recipientId);
                interview.setType(fromJson.get("type"));
                interview.setDurationTime(fromJson.get("duration"));
                interview.setLocation(fromJson.get("location"));
                interview.setHrNotes(fromJson.get("notes"));
                interview.setScheduledTime(fromJson.get("datetime"));
                interview.setJobName(fromJson.get("position"));
                interview.setInterviewLink(fromJson.get("meetingLink"));
                interview.setStatus(InterviewStatusEnum.PENDING_RECEIPT);
                interview.setInterviewInfo(fromJson.get("interviewer"));
                interview.setDirection(fromJson.get("direction"));
                interviewService.save(interview);

                fromJson.put("id", interview.getId().toString());
                chatMessage.setContent(gson.toJson(fromJson));
                chatMessageService.updateById(chatMessage);
            }

            return conversationService.updateById(conversations);
        }
        return false;
    }

    @Override
    public List<MessageVO> history(String sendId, String receiveId) {

        return chatMessageService.list(new LambdaQueryWrapper<ChatMessage>()
                        .eq(ChatMessage::getSenderId, sendId)
                        .eq(ChatMessage::getRecipientId, receiveId)
                        .or(wrapper -> wrapper.eq(ChatMessage::getSenderId, receiveId)
                                .eq(ChatMessage::getRecipientId, sendId))
                        .orderByDesc(ChatMessage::getSendAt))
                .stream()
                .map(chatMessage -> MessageVO.builder()
                        .id(chatMessage.getId().toString())
                        .senderId(chatMessage.getSenderId())
                        .senderName(sysUserService.getById(chatMessage.getSenderId()).getNickname())
                        .text(chatMessage.getContent())
                        .timestamp(chatMessage.getSendAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .isMe(chatMessage.getSenderId().equals(sendId))
                        .avatarUrl(sysUserService.getById(chatMessage.getSenderId()).getAvatar())
                        .isFile(false)
                        .fileName(null)
                        .type(chatMessage.getMessageType().name())
                        .fileUrl(null)
                        .build())
                .toList();

    }

    @Override
    public List<MessageVO> historyByChatId(String chatId) {
        String userId = Optional.ofNullable(SecurityUtils.getCurrentUserId())
                .map(String::valueOf)
                .orElseThrow(() -> new BusinessException("用户未登录"));
        return chatMessageService.list(new LambdaQueryWrapper<ChatMessage>()
                        .eq(ChatMessage::getConversationId, chatId))
                .stream()
                .map(chatMessage -> MessageVO.builder()
                        .id(chatMessage.getId().toString())
                        .senderId(chatMessage.getSenderId())
                        .senderName(sysUserService.getById(chatMessage.getSenderId()).getNickname())
                        .text(chatMessage.getContent())
                        .timestamp(chatMessage.getSendAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .isMe(chatMessage.getSenderId().equals(userId))
                        .avatarUrl(sysUserService.getById(chatMessage.getSenderId()).getAvatar())
                        .isFile(false)
                        .fileName(null)
                        .fileUrl(null)
                        .build())
                .toList();


    }

}
