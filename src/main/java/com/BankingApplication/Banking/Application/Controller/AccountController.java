package com.BankingApplication.Banking.Application.Controller;

import com.BankingApplication.Banking.Application.DTO.AccountDTO;
import com.BankingApplication.Banking.Application.DTO.TransactionDTO;
import com.BankingApplication.Banking.Application.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long accountId){
        AccountDTO accountDTO = accountService.getAccountById(accountId);
        return new ResponseEntity<>(accountDTO,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts(){
        List<AccountDTO> accountDTO = accountService.getAllAccounts();
        return new ResponseEntity<>(accountDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO){
        AccountDTO createdAccount = accountService.createAccount(accountDTO);
        return new ResponseEntity<>(createdAccount,HttpStatus.OK);
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable Long accountId, @RequestBody AccountDTO accountDTO){
        AccountDTO updatedAccount = accountService.updateAccount(accountId,accountDTO);
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId){
        accountService.deleteAccount(accountId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{accountId}/deposit")
   public ResponseEntity<String> depositFund(@PathVariable Long accountId, @RequestParam Double amount){
        String depositFunds = accountService.depositFunds(accountId,amount);
        return ResponseEntity.ok(depositFunds);
   }

    @PostMapping("/{fromAccountId}/transfer/{toAccountId}")
    public ResponseEntity<String> transferFunds(
            @PathVariable Long fromAccountId,
            @PathVariable Long toAccountId,
            @RequestParam double amount) {

        String response = accountService.transferFunds(fromAccountId, toAccountId, amount);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{accountId}/withdraw")
    public ResponseEntity<String> withdrawFunds(
            @PathVariable Long accountId,
            @RequestParam double amount) {

        String response = accountService.withdrawFunds(accountId, amount);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{accountId}/balance")
    public ResponseEntity<Double> getAccountBalance(@PathVariable Long accountId) {
        double balance = accountService.getAccountBalance(accountId);
        return ResponseEntity.ok(balance);
    }

    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<List<TransactionDTO>> getAccountTransactions(@PathVariable Long accountId) {
        List<TransactionDTO> transactions = accountService.getAccountTransactions(accountId);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/user/{userId}/accounts")
    public ResponseEntity<List<AccountDTO>> getAccountsByUserId(@PathVariable Long userId) {
        List<AccountDTO> accounts = accountService.getAccountsByUserId(userId);
        return ResponseEntity.ok(accounts);
    }

    @DeleteMapping("/{accountId}/close")
    public ResponseEntity<String> closeAccount(@PathVariable Long accountId) {
        accountService.closeAccount(accountId);
        return ResponseEntity.ok("Account closed successfully");
    }
}
