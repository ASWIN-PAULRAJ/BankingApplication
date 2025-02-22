package com.BankingApplication.Banking.Application.Repositary;

import com.BankingApplication.Banking.Application.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    List<Transaction> findByAccount_AccountId(Long accountId);

    List<Transaction> findByUser_UserId(Long userId);

    List<Transaction> findByTransactionType(String transactionType);
}
