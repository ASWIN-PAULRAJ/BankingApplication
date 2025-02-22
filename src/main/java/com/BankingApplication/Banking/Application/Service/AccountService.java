package com.BankingApplication.Banking.Application.Service;

import com.BankingApplication.Banking.Application.DTO.AccountDTO;
import com.BankingApplication.Banking.Application.DTO.TransactionDTO;
import com.BankingApplication.Banking.Application.DTO.UserDTO;

import java.util.List;

public interface AccountService {
    AccountDTO createAccount(AccountDTO accountDTO);

    // Get account by ID
    AccountDTO getAccountById(Long accountId);

    // Get all accounts
    List<AccountDTO> getAllAccounts();

    // Update account details
    AccountDTO updateAccount(Long accountId, AccountDTO accountDTO);

    // Delete an account by ID
    void deleteAccount(Long accountId);

    // Transfer funds between accounts
    String transferFunds(Long fromAccountId, Long toAccountId, double amount);

    // Deposit funds into an account
    String depositFunds(Long accountId, double amount);

    // Withdraw funds from an account
    String withdrawFunds(Long accountId, double amount);

    // Get balance of an account
    double getAccountBalance(Long accountId);

    // Get transaction history for an account
    List<TransactionDTO> getAccountTransactions(Long accountId);

    // Get all accounts for a user
    List<AccountDTO> getAccountsByUserId(Long userId);

    // Close an account
    void closeAccount(Long accountId);
}
