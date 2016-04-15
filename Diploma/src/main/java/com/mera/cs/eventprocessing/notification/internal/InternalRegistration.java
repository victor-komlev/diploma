package com.mera.cs.eventprocessing.notification.internal;

import reactor.bus.registry.Registration;

class InternalRegistration {

    @SuppressWarnings("rawtypes")
    private Registration reactorRegistration;

    @SuppressWarnings("rawtypes")
    InternalRegistration(Registration reactorRegistration) {
        this.reactorRegistration = reactorRegistration;
    }

    void terminate() {
        reactorRegistration.cancelAfterUse().cancel();
    }
    
    boolean isCanceled() {
        return reactorRegistration.isCancelled();
    }
}
