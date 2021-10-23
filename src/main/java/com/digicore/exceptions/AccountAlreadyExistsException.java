package com.digicore.exceptions;

public class AccountAlreadyExistsException extends DigicoreException {
    public AccountAlreadyExistsException() {
        super();
    }

    public AccountAlreadyExistsException(String message) {
        super(message);
    }

    public AccountAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
