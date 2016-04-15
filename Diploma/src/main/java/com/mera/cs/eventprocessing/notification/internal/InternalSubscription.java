package com.mera.cs.eventprocessing.notification.internal;

import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;

import com.mera.cs.eventprocessing.exception.NotificationSendException;
import com.mera.cs.eventprocessing.notification.IEvent;

class InternalSubscription {

    private Map<String, InternalRegistration> registrations;

    private InternalClient client;

    InternalSubscription(InternalClient client) {
        this.client = client;
        registrations = new Hashtable<>();
    }

    void fireEvent(IEvent event) throws NotificationSendException {
        boolean fire = false;
        for (Entry<String, InternalRegistration> entry : registrations.entrySet()) {
            if (!entry.getValue().isCanceled()) {
                fire = true;
            }
        }
        if (fire) {
            client.onEvent(event);
        }
    }

    void addRegistration(String topic, InternalRegistration mbRegistration) {
        registrations.put(topic, mbRegistration);
    }

    public void cancel() {
        for (Entry<String, InternalRegistration> entry : registrations.entrySet()) {
            entry.getValue().terminate();
        }
    }

}
