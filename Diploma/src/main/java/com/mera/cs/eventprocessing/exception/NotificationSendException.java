package com.mera.cs.eventprocessing.exception;

public class NotificationSendException extends Exception {

    private static final long serialVersionUID = 1L;

    public NotificationSendException() {
    }

    public NotificationSendException(String message) {
        super(message);
    }

    public NotificationSendException(Throwable cause) {
        super(cause);
    }

    public NotificationSendException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotificationSendException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
