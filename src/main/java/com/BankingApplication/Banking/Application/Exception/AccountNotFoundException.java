package com.BankingApplication.Banking.Application.Exception;

public class AccountNotFoundException extends BankingException{
    public AccountNotFoundException(Long accountId){
        super("User with Id "+accountId+" not found");
    }

    public AccountNotFoundException(String message){
        super(message);
    }
}
