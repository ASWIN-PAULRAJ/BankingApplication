package com.BankingApplication.Banking.Application.Exception;

public class TransactionNotFoundException extends BankingException{
    public TransactionNotFoundException(Long transactionId){
        super("Transaction with ID " + transactionId + " not found");
    }
}
