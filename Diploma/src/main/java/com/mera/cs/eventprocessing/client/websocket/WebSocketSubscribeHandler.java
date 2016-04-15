package com.mera.cs.eventprocessing.client.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

public class WebSocketSubscribeHandler extends AbstractWebSocketHandler {

    private static final Logger LOG = LoggerFactory.getLogger(WebSocketSubscribeHandler.class);

    @Autowired
    private WebSocketConnectionManager manager;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        LOG.info("afterConnectionEstablished " + session);
        manager.processConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        LOG.info("afterConnectionClosed {} {}", session, status);
        manager.processConnectionClosed(session, status);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        LOG.info("handleTransportError {} {}", session, exception);
        manager.processTransportError(session, exception);
        manager.processConnectionClosed(session, CloseStatus.SERVER_ERROR);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        LOG.info("handleTextMessage {}  {}", session, message);
        manager.processTextMessage(session, message);
    }
}
