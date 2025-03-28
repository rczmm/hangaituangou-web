package com.dsy.hangaituangou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsy.hangaituangou.domain.Conversations;
import com.dsy.hangaituangou.mapper.ConversationMapper;
import com.dsy.hangaituangou.service.ConversationService;
import org.springframework.stereotype.Service;

@Service
public class ConversationServiceImpl extends ServiceImpl<ConversationMapper, Conversations> implements ConversationService {
}
