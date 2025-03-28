package com.dsy.hangaituangou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dsy.hangaituangou.domain.Conversations;
import com.dsy.hangaituangou.domain.SysUser;
import com.dsy.hangaituangou.domain.bo.ChatBO;
import com.dsy.hangaituangou.domain.vo.ChatVO;
import com.dsy.hangaituangou.exception.base.BusinessException;
import com.dsy.hangaituangou.service.ChatService;
import com.dsy.hangaituangou.service.ConversationService;
import com.dsy.hangaituangou.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ConversationService conversationService;

    private final SysUserService sysUserService;


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
                        .lastMessageTime(conversations.getLastMessageAt().toString())
                        .build())
                .toList();


    }

    @Override
    public Boolean create(ChatBO chatBO) {
        return conversationService.save(Conversations.builder()
                .conversationType("private")
                .name(chatBO.getRecipientId())
                .senderId(chatBO.getSenderId())
                .recipientId(chatBO.getRecipientId())
                .build());
    }

}
