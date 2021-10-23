package com.digicore.exceptions;

public class DigicoreException extends Throwable {
    public DigicoreException() {
        super();
    }

    public DigicoreException(String message) {
        super(message);
    }

    public DigicoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public DigicoreException(Throwable cause) {
        super(cause);
    }
}
