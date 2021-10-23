package com.digicore.exceptions;

public class ExcessWithdrawnAmountException extends DigicoreException {
    public ExcessWithdrawnAmountException() {
        super();
    }

    public ExcessWithdrawnAmountException(String message) {
        super(message);
    }

    public ExcessWithdrawnAmountException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExcessWithdrawnAmountException(Throwable cause) {
        super(cause);
    }
}
