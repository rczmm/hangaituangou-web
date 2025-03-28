package com.dsy.hangaituangou.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private final Map<String, String> userSessions = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String userId = getUserIdFromSession(session); // Implement this method
        if (userId != null) {
            sessions.put(session.getId(), session);
            userSessions.put(userId, session.getId());
        } else {
            sessions.put(session.getId(), session);
        }
    }

    private String getUserIdFromSession(WebSocketSession session) {
        return Objects.requireNonNull(session.getUri()).getQuery().split("=")[1];
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        String sessionId = session.getId();
        String userIdToRemove = null;
        for (Map.Entry<String, String> entry : userSessions.entrySet()) {
            if (entry.getValue().equals(sessionId)) {
                userIdToRemove = entry.getKey();
                break;
            }
        }
        if (userIdToRemove != null) {
            userSessions.remove(userIdToRemove);
        }
        sessions.remove(sessionId);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();

        try {
            JsonNode jsonNode = objectMapper.readTree(payload);
            String recipientId = jsonNode.get("recipientId").asText();
            String text = jsonNode.get("text").asText();
            String senderId = getUserIdFromSession(session); // Get the sender's ID

            if (recipientId != null && senderId != null) {
                String recipientSessionId = userSessions.get(recipientId);
                if (recipientSessionId != null) {
                    WebSocketSession recipientSession = sessions.get(recipientSessionId);
                    if (recipientSession != null && recipientSession.isOpen()) {
                        // 构造要发送给收件人的消息
                        String messageToSend = objectMapper.writeValueAsString(Map.of(
                                "senderId", senderId,
                                "text", text + "回复"
                        ));
                        recipientSession.sendMessage(new TextMessage(messageToSend));
                        session.sendMessage(new TextMessage("Message sent to " + recipientId));
                    } else {
                        session.sendMessage(new TextMessage("Recipient " + recipientId + " is not online."));
                    }
                } else {
                    session.sendMessage(new TextMessage("Recipient " + recipientId + " not found."));
                }
            } else {
                session.sendMessage(new TextMessage("Invalid message format or missing recipientId."));
            }

        } catch (IOException e) {
            session.sendMessage(new TextMessage("Invalid message format."));
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        System.err.println("Transport error occurred for session " + session.getId() + ": " + exception.getMessage());
    }
}
