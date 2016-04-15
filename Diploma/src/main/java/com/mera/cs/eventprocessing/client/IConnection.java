package com.mera.cs.eventprocessing.client;

import com.mera.cs.eventprocessing.exception.NotificationSendException;
import com.mera.cs.eventprocessing.notification.IEvent;

public interface IConnection {

    void send(IEvent payload) throws NotificationSendException;

}
