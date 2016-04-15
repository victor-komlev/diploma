package com.mera.cs.eventprocessing.notification;

public interface INotificationPublisher {

    void publishEvent(IEvent event);
}
