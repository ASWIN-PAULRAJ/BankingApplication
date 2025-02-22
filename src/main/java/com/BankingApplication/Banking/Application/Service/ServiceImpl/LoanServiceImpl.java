package com.BankingApplication.Banking.Application.Service.ServiceImpl;

import com.BankingApplication.Banking.Application.DTO.LoanDTO;
import com.BankingApplication.Banking.Application.Entity.Loan;
import com.BankingApplication.Banking.Application.Entity.User;
import com.BankingApplication.Banking.Application.Exception.LoanNotFoundException;
import com.BankingApplication.Banking.Application.Exception.UserNotFoundException;
import com.BankingApplication.Banking.Application.Mapper.LoanMapper;
import com.BankingApplication.Banking.Application.Repositary.LoanRepository;
import com.BankingApplication.Banking.Application.Repositary.UserRepository;
import com.BankingApplication.Banking.Application.Service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    LoanMapper loanMapper;

    @Override
    public LoanDTO createLoan(LoanDTO loanDTO) {
        User user = userRepository.findById(loanDTO.getUserId()).orElseThrow(()-> new UserNotFoundException(loanDTO.getUserId()));

        Loan loan = loanMapper.mapToEntity(loanDTO);

        loan.setUser(user);
        Loan savedLoan = loanRepository.save(loan);

        return loanMapper.mapToDTO(savedLoan);
    }

    @Override
    public LoanDTO getLoanById(Long loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow(()-> new LoanNotFoundException("Loan with id "+loanId+ " is not found"));

        return loanMapper.mapToDTO(loan);
    }

    @Override
    public List<LoanDTO> getLoansByUserId(Long userId) {
        List<Loan> loans = loanRepository.findByUser_UserId(userId);
        return loans.stream().map(loan -> loanMapper.mapToDTO(loan)).collect(Collectors.toList());
    }

    @Override
    public List<LoanDTO> getLoansByStatus(String status) {

        List<Loan> loans = loanRepository.findByStatus(status.toUpperCase());

        return loans.stream().map(loan -> loanMapper.mapToDTO(loan)).collect(Collectors.toList());
    }

    @Override
    public List<LoanDTO> getAllLoans() {
        List<Loan> loans = loanRepository.findAll();
        return loans.stream().map(loan -> loanMapper.mapToDTO(loan)).collect(Collectors.toList());
    }

    @Override
    public  List<LoanDTO> getLoansByType(String type) {
        List<Loan> loans = loanRepository.findByLoanType(type.toUpperCase());
        return loans.stream().map(loan -> loanMapper.mapToDTO(loan)).collect(Collectors.toList());
    }


}
