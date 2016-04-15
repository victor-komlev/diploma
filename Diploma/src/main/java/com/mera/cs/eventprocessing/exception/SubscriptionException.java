package com.mera.cs.eventprocessing.exception;

public class SubscriptionException extends Exception {

    private static final long serialVersionUID = 1L;

    public SubscriptionException() {
    }

    public SubscriptionException(String arg0) {
        super(arg0);
    }

    public SubscriptionException(Throwable arg0) {
        super(arg0);
    }

    public SubscriptionException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public SubscriptionException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

}
