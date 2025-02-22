package com.BankingApplication.Banking.Application.Exception;

public class UserNotFoundException extends BankingException{
    public UserNotFoundException(Long userId) {
        super("User with Id "+userId+" not found");

    }
    public UserNotFoundException(String firstName, String lastName){
        super("User with first name "+firstName+ " and last name "+lastName+"is not found ");

    }
}
