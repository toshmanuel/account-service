package com.digicore.exceptions;

public class AccountDoesNotExistException extends DigicoreException{
    public AccountDoesNotExistException() {
        super();
    }

    public AccountDoesNotExistException(String message) {
        super(message);
    }

    public AccountDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountDoesNotExistException(Throwable cause) {
        super(cause);
    }
}
