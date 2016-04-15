package com.mera.cs.eventprocessing.notification.internal;

import com.mera.cs.eventprocessing.client.IConnection;
import com.mera.cs.eventprocessing.exception.NotificationSendException;
import com.mera.cs.eventprocessing.notification.IEvent;

class InternalClient {

    private String clientId;
    private String sessionId;
    private IConnection connection;

    InternalClient() {
    }

    InternalClient(String clientId, String sessionId, IConnection connection) {
        super();
        this.clientId = clientId;
        this.sessionId = sessionId;
        this.connection = connection;
    }

    String getClientId() {
        return clientId;
    }

    String getSessionId() {
        return sessionId;
    }

    void onEvent(IEvent event) throws NotificationSendException {
        connection.send(event);
    }

}
