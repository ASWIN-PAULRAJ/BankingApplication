package com.BankingApplication.Banking.Application.Exception;

public class InSufficientBalance extends BankingException{

    public InSufficientBalance(String message) {
        super(message);
    }
}
