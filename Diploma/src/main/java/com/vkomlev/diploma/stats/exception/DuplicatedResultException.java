package com.vkomlev.diploma.stats.exception;

public class DuplicatedResultException extends Exception {

    private static final long serialVersionUID = 1L;

    public DuplicatedResultException() {
        super();
    }

    public DuplicatedResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatedResultException(String message) {
        super(message);
    }

    public DuplicatedResultException(Throwable cause) {
        super(cause);
    }

}
