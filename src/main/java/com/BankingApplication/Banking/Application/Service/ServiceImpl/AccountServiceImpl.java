package com.BankingApplication.Banking.Application.Service.ServiceImpl;

import com.BankingApplication.Banking.Application.DTO.AccountDTO;
import com.BankingApplication.Banking.Application.DTO.TransactionDTO;
import com.BankingApplication.Banking.Application.DTO.UserDTO;
import com.BankingApplication.Banking.Application.Entity.Account;
import com.BankingApplication.Banking.Application.Entity.User;
import com.BankingApplication.Banking.Application.Exception.AccountNotFoundException;
import com.BankingApplication.Banking.Application.Exception.InSufficientBalance;
import com.BankingApplication.Banking.Application.Exception.UserNotFoundException;
import com.BankingApplication.Banking.Application.Mapper.AccountMapper;
import com.BankingApplication.Banking.Application.Mapper.TransactionMapper;
import com.BankingApplication.Banking.Application.Repositary.AccountRepository;
import com.BankingApplication.Banking.Application.Repositary.UserRepository;
import com.BankingApplication.Banking.Application.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private TransactionMapper tarnsactionMapper;


    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {

        User user = userRepository.findById(accountDTO.getUserDTO().getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Account account = accountMapper.mapToEntity(accountDTO);
        account.setUser(user);

        account = accountRepository.save(account);

        return accountMapper.mapToDTO(account);
    }



    @Override
    public AccountDTO getAccountById(Long accountId) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if(accountOptional.isEmpty()){
            throw  new AccountNotFoundException(accountId);
        }
        return accountMapper.mapToDTO(accountOptional.get());
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accountMapper.mapToDTOList(accounts);
    }

    @Override
    public AccountDTO updateAccount(Long accountId, AccountDTO accountDTO) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if(accountOptional.isEmpty()){
            throw new AccountNotFoundException(accountId);
        }
        Account account = accountOptional.get();
        account.setBalance(accountDTO.getBalance());
        account.setAccountNumber(accountDTO.getAccountNumber());
        account = accountRepository.save(account);

        return accountMapper.mapToDTO(account);
    }

    @Override
    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }

    @Override
    public String transferFunds(Long fromAccountId, Long toAccountId, double amount) {
        Optional<Account> fromAccountOptional = accountRepository.findById(fromAccountId);
        Optional<Account> toAccountOptional = accountRepository.findById(toAccountId);

        if(fromAccountOptional.isEmpty() || toAccountOptional.isEmpty()){
            throw new AccountNotFoundException("One or both accounts are not found ");
        }

        Account fromAccount = fromAccountOptional.get();
        Account toAccount = toAccountOptional.get();

        if(fromAccount.getBalance()< amount){
            throw new InSufficientBalance("Sorry InSufficient Balance");
        }

        fromAccount.setBalance(fromAccount.getBalance() -amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        return "Transfer Successful";
    }

    @Override
    public String depositFunds(Long accountId, double amount) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if(accountOptional.isEmpty()){
            throw new AccountNotFoundException(accountId);
        }

        Account account = accountOptional.get();

        account.setBalance(account.getBalance()+amount);
        accountRepository.save(account);

        return "Deposit Successful";
    }

    @Override
    public String withdrawFunds(Long accountId, double amount) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if(accountOptional.isEmpty()){
            throw  new AccountNotFoundException(accountId);
        }
        Account account = accountOptional.get();
        if(account.getBalance() <amount){
            throw new InSufficientBalance("Insufficient Balance");
        }

        account.setBalance(account.getBalance()-amount);
        accountRepository.save(account);

        return "Withdraw Successful";
    }

    @Override
    public double getAccountBalance(Long accountId) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if(accountOptional.isEmpty()){
            throw new AccountNotFoundException(accountId);
        }
        return accountOptional.get().getBalance();

    }

    @Override
    public List<TransactionDTO> getAccountTransactions(Long accountId) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if(accountOptional.isEmpty()){
            throw new AccountNotFoundException(accountId);
        }


        return accountOptional.get().getTransactions().stream()
                .map(transaction -> tarnsactionMapper.mapToDTO(transaction)).toList();
    }

    @Override
    public List<AccountDTO> getAccountsByUserId(Long userId) {
        List<Account> accounts = accountRepository.findAllByUser_UserId(userId);
        return accountMapper.mapToDTOList(accounts);
    }

    @Override
    public void closeAccount(Long accountId) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if(accountOptional.isEmpty()){
            throw new AccountNotFoundException(accountId);
        }

        Account account = accountOptional.get();

        accountRepository.delete(account);
    }
}
