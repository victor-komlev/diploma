package com.mera.cs.eventprocessing.client.websocket;

import java.io.IOException;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mera.cs.eventprocessing.client.IConnection;
import com.mera.cs.eventprocessing.exception.NotificationSendException;
import com.mera.cs.eventprocessing.notification.IEvent;

public class WebSocketConnection implements IConnection {

    private WebSocketSession internalSession;
    private ObjectMapper objectMapper;

    public WebSocketConnection(WebSocketSession internalSession, ObjectMapper objectMapper) {
        this.internalSession = internalSession;
        this.objectMapper = objectMapper;
    }

    @Override
    public void send(IEvent payload) throws NotificationSendException {
        try {
            internalSession.sendMessage(new TextMessage(objectMapper.writeValueAsBytes(payload)));
        } catch (Exception e) {
            throw new NotificationSendException(e);
        }
    }

    public void close() throws IOException {
        internalSession.close();
    }

}
