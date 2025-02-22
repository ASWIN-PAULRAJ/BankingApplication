package com.BankingApplication.Banking.Application.Exception;

public class LoanNotFoundException extends RuntimeException{


    public LoanNotFoundException(String message){
        super(message);
    }
}
