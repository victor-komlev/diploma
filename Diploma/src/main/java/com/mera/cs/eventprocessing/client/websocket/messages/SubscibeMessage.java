package com.mera.cs.eventprocessing.client.websocket.messages;

public class SubscibeMessage {

    private String sessionId;
    private String clientType;

    public SubscibeMessage() {
    }

    public SubscibeMessage(String sessionId, String clientType) {
        super();
        this.sessionId = sessionId;
        this.clientType = clientType;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((clientType == null) ? 0 : clientType.hashCode());
        result = prime * result + ((sessionId == null) ? 0 : sessionId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SubscibeMessage other = (SubscibeMessage) obj;
        if (clientType == null) {
            if (other.clientType != null)
                return false;
        } else if (!clientType.equals(other.clientType))
            return false;
        if (sessionId == null) {
            if (other.sessionId != null)
                return false;
        } else if (!sessionId.equals(other.sessionId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "SubscibeMessage [sessionId=" + sessionId + ", clientType=" + clientType + "]";
    }

}
