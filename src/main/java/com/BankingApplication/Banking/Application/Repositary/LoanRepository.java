package com.BankingApplication.Banking.Application.Repositary;

import com.BankingApplication.Banking.Application.Entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan,Long> {

    List<Loan> findByUser_UserId(Long userId);


    List<Loan> findByStatus(String status);

    List<Loan> findByLoanType(String loanType);
}
