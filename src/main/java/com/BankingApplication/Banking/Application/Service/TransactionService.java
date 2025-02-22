package com.BankingApplication.Banking.Application.Service;

import com.BankingApplication.Banking.Application.DTO.TransactionDTO;

import java.util.List;

public interface TransactionService {

    TransactionDTO createTransaction(TransactionDTO transactionDTO);

    TransactionDTO getTransactionById(Long transactionId);

    List<TransactionDTO> getTransactionsByAccountId(Long accountId);

    List<TransactionDTO> getTransactionsByUserId(Long userId);

    List<TransactionDTO> getTransactionsByType(String transactionType);

    List<TransactionDTO> getAllTransactions();
}
