package com.digicore.exceptions;

public class InsufficientInitialDepositException extends DigicoreException {
    public InsufficientInitialDepositException() {
        super();
    }

    public InsufficientInitialDepositException(String message) {
        super(message);
    }

    public InsufficientInitialDepositException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsufficientInitialDepositException(Throwable cause) {
        super(cause);
    }
}
