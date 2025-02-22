package com.BankingApplication.Banking.Application.DTO;

import jakarta.validation.constraints.*;

public class LoanDTO {
    private Long loanId;

    @NotBlank(message = "Loan type is required")
    private String loanType;

    @NotNull(message = "Loan amount is required")
    @Min(value = 1000, message = "Loan amount must be at least 1000")
    private Double loanAmount;

    @NotNull(message = "Interest rate is required")
    @DecimalMin(value = "0.1", message = "Interest rate must be at least 0.1%")
    private Double interestRate;

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "PENDING|APPROVED|REJECTED", message = "Status must be PENDING, APPROVED, or REJECTED")
    private String status;

    @NotNull(message = "User ID is required")
    private Long userId;

    public LoanDTO() {

    }

    public LoanDTO(Long loanId, String loanType, Double loanAmount, Double interestRate, String status, Long userId) {
        this.loanId = loanId;
        this.loanType = loanType;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.status = status;
        this.userId = userId;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
