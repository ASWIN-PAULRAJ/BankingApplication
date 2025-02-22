package com.BankingApplication.Banking.Application.Controller;

import com.BankingApplication.Banking.Application.DTO.TransactionDTO;
import com.BankingApplication.Banking.Application.Entity.Transaction;
import com.BankingApplication.Banking.Application.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/create")
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        TransactionDTO createdTransaction = transactionService.createTransaction(transactionDTO);
        return ResponseEntity.ok(createdTransaction);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Long transactionId) {
        TransactionDTO transactionDTO = transactionService.getTransactionById(transactionId);
        return ResponseEntity.ok(transactionDTO);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByAccountId(@PathVariable Long accountId) {
        List<TransactionDTO> transactionDTOs = transactionService.getTransactionsByAccountId(accountId);
        return ResponseEntity.ok(transactionDTOs);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TransactionDTO>> getTransactionByUserId(@PathVariable Long userId){
        List<TransactionDTO> transactionDTOs = transactionService.getTransactionsByUserId(userId);
        return ResponseEntity.ok(transactionDTOs);
    }

    @GetMapping("/type/{transactionType}")
    public ResponseEntity<List<TransactionDTO>> getTransactionByType(@PathVariable String transactionType){
        List<TransactionDTO> transactionDTOs = transactionService.getTransactionsByType(transactionType);
        return ResponseEntity.ok(transactionDTOs);

    }

    @GetMapping("/all")
    public ResponseEntity<List<TransactionDTO>> getAllTransactions(){
        List<TransactionDTO> transactionDTOs = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactionDTOs);
    }

}
