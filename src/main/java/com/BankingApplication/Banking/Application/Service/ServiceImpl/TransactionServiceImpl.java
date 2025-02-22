package com.BankingApplication.Banking.Application.Service.ServiceImpl;

import com.BankingApplication.Banking.Application.DTO.TransactionDTO;
import com.BankingApplication.Banking.Application.Entity.Account;
import com.BankingApplication.Banking.Application.Entity.Transaction;
import com.BankingApplication.Banking.Application.Entity.User;
import com.BankingApplication.Banking.Application.Exception.AccountNotFoundException;
import com.BankingApplication.Banking.Application.Exception.TransactionNotFoundException;
import com.BankingApplication.Banking.Application.Exception.UserNotFoundException;
import com.BankingApplication.Banking.Application.Mapper.TransactionMapper;
import com.BankingApplication.Banking.Application.Repositary.AccountRepository;
import com.BankingApplication.Banking.Application.Repositary.TransactionRepository;
import com.BankingApplication.Banking.Application.Repositary.UserRepository;
import com.BankingApplication.Banking.Application.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {


    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionMapper transactionMapper;


    @Override
    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {

        User user = userRepository.findById(transactionDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException(transactionDTO.getUserId()));

        Account account = accountRepository.findById(transactionDTO.getAccountId())
                .orElseThrow(() -> new AccountNotFoundException(transactionDTO.getAccountId()));

        String transactionType = transactionDTO.getTransactionType().toUpperCase();
        if (!transactionType.equals("DEPOSIT") && !transactionType.equals("WITHDRAWAL") && !transactionType.equals("TRANSFER")) {
            throw new IllegalArgumentException("Invalid transaction type: " + transactionType);
        }

        if ((transactionType.equals("WITHDRAWAL") || transactionType.equals("TRANSFER"))
                && account.getBalance() < transactionDTO.getAmount()) {
            throw new IllegalArgumentException("Insufficient balance for this transaction.");
        }


        if (transactionType.equals("DEPOSIT")) {
            account.setBalance(account.getBalance() + transactionDTO.getAmount());
        } else { // Withdrawal or Transfer
            account.setBalance(account.getBalance() - transactionDTO.getAmount());
        }


        Transaction transaction = transactionMapper.mapToEntity(transactionDTO);
        transaction.setTransactionDate(new Date());
        transaction.setAccount(account);
        transaction.setUser(user);
        transactionRepository.save(transaction);


        accountRepository.save(account);

        return transactionMapper.mapToDTO(transaction);
    }

    @Override
    public TransactionDTO getTransactionById(Long transactionId) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(transactionId);
        if (transactionOptional.isEmpty()) {
            throw new TransactionNotFoundException(transactionId);
        }
        return transactionMapper.mapToDTO(transactionOptional.get());
    }

    @Override
    public List<TransactionDTO> getTransactionsByAccountId(Long accountId) {
        List<Transaction> transactions = transactionRepository.findByAccount_AccountId(accountId);

        if (transactions.isEmpty()) {
            throw new TransactionNotFoundException(accountId);
        }

        return transactions.stream().map(transaction -> transactionMapper.mapToDTO(transaction)).toList();
    }


    @Override
    public List<TransactionDTO> getTransactionsByUserId(Long userId) {
       List<Transaction> transactions = transactionRepository.findByUser_UserId(userId);

       if(transactions.isEmpty()){
           throw new TransactionNotFoundException(userId);
       }

       return transactions.stream().map( transaction -> transactionMapper.mapToDTO(transaction)).toList();
    }

    @Override
    public List<TransactionDTO> getTransactionsByType(String transactionType) {
        List<Transaction> transactions = transactionRepository.findByTransactionType(transactionType);

        return transactions.stream()
                .map(transaction -> transactionMapper.mapToDTO(transaction)) // Lambda expression
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();

        return transactions.stream().map(transaction -> transactionMapper.mapToDTO(transaction)).collect(Collectors.toList());
    }
}
