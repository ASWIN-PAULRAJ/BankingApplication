package com.BankingApplication.Banking.Application.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

public class TransactionDTO {

    private Long transactionId;

    @NotNull(message = "Amount is required")
    @Min(value = 1, message = "Amount must be greater than 0")
    private Double amount;

    @NotNull(message = "Transaction date is required")
    private Date transactionDate;

    @NotBlank(message = "Transaction type is required")
    @Pattern(regexp = "DEPOSIT|WITHDRAWAL|TRANSFER", message = "Transaction type must be DEPOSIT, WITHDRAWAL, or TRANSFER")
    private String transactionType;

    @NotNull(message = "Account ID is required")
    private Long accountId;

    @NotNull(message = "User ID is required")
    private Long userId;


    public TransactionDTO() {
    }

    public TransactionDTO(Long transactionId, Double amount, Date transactionDate, String transactionType, Long accountId, Long userId) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.accountId = accountId;
        this.userId = userId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
