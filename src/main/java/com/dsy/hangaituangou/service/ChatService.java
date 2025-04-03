package com.dsy.hangaituangou.service;

import com.dsy.hangaituangou.domain.bo.ChatBO;
import com.dsy.hangaituangou.domain.vo.ChatVO;
import com.dsy.hangaituangou.domain.vo.MessageVO;

import java.util.List;

public interface ChatService {
    List<ChatVO> list(String userId);

    Boolean create(ChatBO chatBO);

    Boolean saveMessage(String senderId, String recipientId, String text);

    List<MessageVO> history(String sendId, String receiveId);

    List<MessageVO> historyByChatId(String chatId);
}
