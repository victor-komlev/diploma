package com.mera.cs.eventprocessing.notification.internal;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.mera.cs.eventprocessing.client.IConnection;
import com.mera.cs.eventprocessing.exception.SubscriptionException;
import com.mera.cs.eventprocessing.notification.ISubsciptionTopicLookupHelper;
import com.mera.cs.eventprocessing.notification.ISubscriptionManager;

class InternalSubscriptionManager implements ISubscriptionManager, InitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(InternalSubscriptionManager.class);

    @Autowired
    private InternalEventBusManager eventBusManager;

    @Autowired
    private ISubsciptionTopicLookupHelper topicHelper;

    private Map<String, InternalSubscription> clients;

    @Override
    public void subscribeClient(String clientId, String sessionId, IConnection connection)
            throws SubscriptionException {
        InternalClient client = new InternalClient(clientId, sessionId, connection);
        validate(client);
        InternalSubscription mbSubscription = subscribe(client);
        clients.put(client.getClientId(), mbSubscription);
    }

    @Override
    public void unsubscribeClient(String clientId) throws SubscriptionException {
        InternalSubscription mbSubscription = clients.remove(clientId);
        if (mbSubscription == null) {
            throw new SubscriptionException("Client has not been subscribed: " + clientId);
        }
        mbSubscription.cancel();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        clients = new ConcurrentHashMap<>();
    }

    // Internal Methods
    private InternalSubscription subscribe(InternalClient client) throws SubscriptionException {
        InternalSubscription mbSubscription = new InternalSubscription(client);
        Map<String, List<String>> subscriptionTopics = topicHelper.lookupTopics(client.getSessionId());
        for (Entry<String, List<String>> entry : subscriptionTopics.entrySet()) {
            InternalRegistration mbRegistration = null;
            for (String topic : entry.getValue()) {
                mbRegistration = eventBusManager.register(entry.getKey(), topic, ev -> {
                    try {
                        mbSubscription.fireEvent(ev.getData());
                    } catch (Exception e) {
                        String message = "Failed to send notification to" + mbSubscription;
                        LOG.error(message, e);
                    }
                });
                mbSubscription.addRegistration(topic, mbRegistration);
            }
        }
        return mbSubscription;
    }

    private void validate(InternalClient client) throws SubscriptionException {
        if (client == null || client.getClientId() == null || client.getSessionId() == null) {
            throw new SubscriptionException("Wrong client!",
                    new IllegalArgumentException("One of the parameters is null! " + client));
        }
        if (clients.containsKey(client.getClientId())) {
            throw new SubscriptionException("Client already subscribed: " + client);
        }
    }
}
