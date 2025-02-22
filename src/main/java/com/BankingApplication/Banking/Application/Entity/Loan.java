package com.BankingApplication.Banking.Application.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity


public class Loan {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loneId;

    private String loanType;
    private Double loanAmount;
    private Double interestRate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Loan() {
    }

    public Loan(Long loneId, String loanType, Double loanAmount, Double interestRate, String status, User user) {
        this.loneId = loneId;
        this.loanType = loanType;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.status = status;
        this.user = user;
    }

    public Long getLoneId() {
        return loneId;
    }

    public void setLoneId(Long loneId) {
        this.loneId = loneId;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
