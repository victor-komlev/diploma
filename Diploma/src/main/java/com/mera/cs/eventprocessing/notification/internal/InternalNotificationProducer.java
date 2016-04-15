package com.mera.cs.eventprocessing.notification.internal;

import org.springframework.beans.factory.annotation.Autowired;

import com.mera.cs.eventprocessing.notification.INotificationPublisher;
import com.mera.cs.eventprocessing.notification.IEvent;

class InternalNotificationProducer implements INotificationPublisher {

    @Autowired
    private InternalEventBusManager busManager;

    @Override
    public void publishEvent(IEvent event) {
        busManager.issueNotification(event);
    }

}
