package com.BankingApplication.Banking.Application.Service;

import com.BankingApplication.Banking.Application.DTO.LoanDTO;

import java.util.List;

public interface LoanService {

    LoanDTO createLoan(LoanDTO loanDTO);

    LoanDTO getLoanById(Long loanId);

    List<LoanDTO> getLoansByUserId(Long userId);

    List<LoanDTO> getLoansByStatus(String status);

    List<LoanDTO> getAllLoans();

    List<LoanDTO> getLoansByType(String type);
}
