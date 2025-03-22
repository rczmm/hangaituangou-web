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
    private final Map<String, String> userSessions = new ConcurrentHashMap<>(); // Map userId to sessionId
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String userId = getUserIdFromSession(session); // Implement this method
        if (userId != null) {
            System.out.println("User connected: " + userId + ", Session ID: " + session.getId());
            sessions.put(session.getId(), session);
            userSessions.put(userId, session.getId());
            // Optionally, notify other online users about this user's presence
        } else {
            System.out.println("Anonymous client connected: " + session.getId());
            sessions.put(session.getId(), session); // Still track anonymous sessions if needed
        }
    }

    private String getUserIdFromSession(WebSocketSession session) {
        // In a real application, you would likely retrieve the user ID from:
        // 1. A query parameter in the WebSocket URL (e.g., ws://localhost:8080/chat?userId=someUser)
        // 2. An attribute in the HTTP session (if using HttpSessionHandshakeInterceptor)
        // 3. A token passed during the handshake

        // For this example, let's assume the userId is passed as a query parameter
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
            System.out.println("User disconnected: " + userIdToRemove + ", Session ID: " + sessionId + " with status: " + status);
            userSessions.remove(userIdToRemove);
        } else {
            System.out.println("Anonymous client disconnected: " + sessionId + " with status: " + status);
        }
        sessions.remove(sessionId);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println("Received message from client " + session.getId() + ": " + payload);

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
                        // （可选）您可能希望将确认发送回发件人
                        session.sendMessage(new TextMessage("Message sent to " + recipientId));
                    } else {
                        // 收件人不在线
                        session.sendMessage(new TextMessage("Recipient " + recipientId + " is not online."));
                    }
                } else {
                    // 找不到收件人
                    session.sendMessage(new TextMessage("Recipient " + recipientId + " not found."));
                }
            } else {
                session.sendMessage(new TextMessage("Invalid message format or missing recipientId."));
            }

        } catch (IOException e) {
            System.err.println("Failed to parse message payload: " + e.getMessage());
            session.sendMessage(new TextMessage("Invalid message format."));
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        System.err.println("Transport error occurred for session " + session.getId() + ": " + exception.getMessage());
    }
}
