package com.vkomlev.diploma.stats.exception;

public class IncorrectTaskException extends Exception {

    private static final long serialVersionUID = 1L;

    public IncorrectTaskException() {
        super();
    }

    public IncorrectTaskException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectTaskException(String message) {
        super(message);
    }

    public IncorrectTaskException(Throwable cause) {
        super(cause);
    }

}
