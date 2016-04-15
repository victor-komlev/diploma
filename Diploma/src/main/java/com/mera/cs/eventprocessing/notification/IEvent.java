package com.mera.cs.eventprocessing.notification;

public interface IEvent {
    String getRoute();
    String getTopic();
}
