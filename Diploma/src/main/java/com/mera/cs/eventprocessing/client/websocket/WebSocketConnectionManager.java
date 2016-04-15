package com.mera.cs.eventprocessing.client.websocket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mera.cs.eventprocessing.client.websocket.messages.SubscibeMessage;
import com.mera.cs.eventprocessing.exception.SubscriptionException;
import com.mera.cs.eventprocessing.notification.ISubscriptionManager;

public class WebSocketConnectionManager {

    private static final Logger LOG = LoggerFactory.getLogger(WebSocketConnectionManager.class);
    private Map<String, WebSocketConnection> connectionMap;

    @Autowired
    private ISubscriptionManager subscriptionManager;

    @Autowired
    private ObjectMapper objectMapper;

    public WebSocketConnectionManager() {
        connectionMap = new ConcurrentHashMap<>();
    }

    public void processConnectionEstablished(WebSocketSession session) throws Exception {
        try {
            LOG.debug("processConnectionEstablished {} Session Type : {} Accepted Protocol {}", session,
                    session.getClass().getName(), session.getAcceptedProtocol());
            String sessionId = session.getId();
            WebSocketConnection mbConnection = new WebSocketConnection(session, objectMapper);
            connectionMap.put(sessionId, mbConnection);
        } catch (Exception e) {
            LOG.error("processConnectionEstablished", e);
            throw e;
        }
    }

    public void processConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        LOG.debug("processConnectionClosed {} {}", session, status);
        try {
            subscriptionManager.unsubscribeClient(session.getId());
            WebSocketConnection mbConnection = connectionMap.remove(session.getId());
            if (mbConnection != null) {
                try {
                    LOG.debug("closing Connection {}", mbConnection);
                    mbConnection.close();
                } catch (Exception e) {
                    LOG.error("Error closing connection", e);
                }
            } else {
                LOG.error("Error closing connection mbConnection == null");
            }
        } catch (SubscriptionException e) {
            LOG.error("processConnectionClosed {}", e.getMessage());
        }
    }

    public void processTransportError(WebSocketSession session, Throwable exception) throws Exception {
        LOG.debug("processTransportError {} {}", session, exception);
    }

    public void processTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        LOG.debug("processTextMessage {}  {}", session, message);
        try {
            SubscibeMessage subscribeMessage = null;
            try {
                subscribeMessage = objectMapper.readValue(message.getPayload(), SubscibeMessage.class);
            } catch (Exception e) {
                LOG.error("Unknown messageType", e);
                return;
            }
            WebSocketConnection connection = connectionMap.get(session.getId());
            if (connection == null) {
                LOG.error("Unknown webSocket session {}", session);
                return;
            }
            subscriptionManager.subscribeClient(session.getId(), subscribeMessage.getSessionId(), connection);
        } catch (SubscriptionException e) {
            LOG.error("processTextMessage {}", e.getMessage());
        }
    }
}
