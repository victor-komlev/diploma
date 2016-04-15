package com.mera.cs.eventprocessing.notification;

import com.mera.cs.eventprocessing.client.IConnection;
import com.mera.cs.eventprocessing.exception.SubscriptionException;

public interface ISubscriptionManager {

    void subscribeClient(String clientId, String SessionId, IConnection connection) throws SubscriptionException;

    void unsubscribeClient(String clientId) throws SubscriptionException;
}
