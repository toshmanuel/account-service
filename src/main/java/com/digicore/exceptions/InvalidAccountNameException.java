package com.digicore.exceptions;

public class InvalidAccountNameException extends DigicoreException {
    public InvalidAccountNameException() {
        super();
    }

    public InvalidAccountNameException(String message) {
        super(message);
    }

    public InvalidAccountNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidAccountNameException(Throwable cause) {
        super(cause);
    }
}
