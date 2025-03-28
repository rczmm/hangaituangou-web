package com.dsy.hangaituangou.controller;

import com.dsy.hangaituangou.domain.bo.ChatBO;
import com.dsy.hangaituangou.domain.vo.ChatVO;
import com.dsy.hangaituangou.domain.vo.MessageVO;
import com.dsy.hangaituangou.domain.vo.base.RespBase;
import com.dsy.hangaituangou.service.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/chat")
@RequiredArgsConstructor
@Tag(name = "会话管理", description = "会话接口")
public class ChatController {

    private final ChatService chatService;


    @Operation(summary = "查询会话列表", description = "查询会话列表")
    @ApiResponses(value = {
            @ApiResponse(description = "查询会话列表成功", responseCode = "200"),
            @ApiResponse(description = "查询会话列表失败", responseCode = "400")}
    )
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public RespBase<List<ChatVO>> list(@RequestParam String userId) {
        return RespBase.success(chatService.list(userId));
    }

    @Operation(summary = "创建会话", description = "创建会话")
    @ApiResponses(value = {
            @ApiResponse(description = "创建会话成功", responseCode = "200"),
            @ApiResponse(description = "创建会话失败", responseCode = "400")}
    )
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public RespBase<Boolean> create(@RequestBody ChatBO chatBO) {
        return RespBase.success(chatService.create(chatBO));
    }

    @Operation(summary = "查询历史消息", description = "查询历史消息")
    @ApiResponses(value = {
            @ApiResponse(description = "查询历史消息成功", responseCode = "200"),
            @ApiResponse(description = "查询历史消息失败", responseCode = "400")}
    )
    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public RespBase<List<MessageVO>> history(@RequestParam String sendId, @RequestParam String receiveId) {
        return RespBase.success(chatService.history(sendId, receiveId));
    }
}
