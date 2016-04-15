package com.mera.cs.eventprocessing.notification.internal;

import static reactor.bus.selector.Selectors.$;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

import com.mera.cs.eventprocessing.exception.SubscriptionException;
import com.mera.cs.eventprocessing.notification.IEvent;

import reactor.bus.Event;
import reactor.bus.EventBus;
import reactor.bus.registry.Registration;
import reactor.core.dispatch.ThreadPoolExecutorDispatcher;
import reactor.fn.Consumer;

class InternalEventBusManager implements InitializingBean {

    static final String INCOMMING_EVENT_TOKEN = "fire_in_the_hole";

    private EventBus incommingEventBus;

    private List<String> busList;

    private int inputThreadpoolSize;
    private int inputQueueSize;

    private int routingThreadpoolSize;
    private int routingQueueSize;

    private Map<String, EventBus> eventBusMap;

    @SuppressWarnings("rawtypes")
    InternalRegistration register(String busName, String topic, Consumer<Event<IEvent>> consumer)
            throws SubscriptionException {
        if (eventBusMap.get(busName) == null) {
            throw new SubscriptionException("No event bus found for name: " + busName);
        }
        Registration reactorRegistration = eventBusMap.get(busName).on($(topic), consumer);

        return new InternalRegistration(reactorRegistration);
    }

    void issueNotification(IEvent event) {
        incommingEventBus.notify(INCOMMING_EVENT_TOKEN, Event.wrap(event));
    }

    public int getInputThreadpoolSize() {
        return inputThreadpoolSize;
    }

    public void setInputThreadpoolSize(int inputThreadpoolSize) {
        this.inputThreadpoolSize = inputThreadpoolSize;
    }

    public int getInputQueueSize() {
        return inputQueueSize;
    }

    public void setInputQueueSize(int inputQueueSize) {
        this.inputQueueSize = inputQueueSize;
    }

    public int getRoutingThreadpoolSize() {
        return routingThreadpoolSize;
    }

    public void setRoutingThreadpoolSize(int routingThreadpoolSize) {
        this.routingThreadpoolSize = routingThreadpoolSize;
    }

    public int getRoutingQueueSize() {
        return routingQueueSize;
    }

    public void setRoutingQueueSize(int routingQueueSize) {
        this.routingQueueSize = routingQueueSize;
    }

    public List<String> getBusList() {
        return busList;
    }

    public void setBusList(List<String> busList) {
        this.busList = busList;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // Configuring routing bus
        eventBusMap = new Hashtable<>();
        for (String eventBusName : busList) {
            EventBus eventBus = EventBus
                    .create(new ThreadPoolExecutorDispatcher(routingThreadpoolSize, routingQueueSize));
            eventBusMap.put(eventBusName, eventBus);
        }

        // Configuring input bus
        incommingEventBus = EventBus.create(new ThreadPoolExecutorDispatcher(inputThreadpoolSize, inputQueueSize));
        incommingEventBus.<Event<IEvent>> on($(INCOMMING_EVENT_TOKEN), e -> {
            IEvent mbEvent = e.getData();
            EventBus targetBus = eventBusMap.get(mbEvent.getRoute());
            if (targetBus != null) {
                targetBus.notify(mbEvent.getTopic(), e);
            }
        });
    }
}
