package com.BankingApplication.Banking.Application.Exception;

public class BankingException extends RuntimeException{
    public BankingException(String message) {
        super(message);
    }

    public BankingException(String message, Throwable cause) {
        super(message, cause);
    }
}
