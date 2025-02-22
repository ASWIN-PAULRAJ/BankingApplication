package com.BankingApplication.Banking.Application.Mapper;

import com.BankingApplication.Banking.Application.DTO.LoanDTO;
import com.BankingApplication.Banking.Application.Entity.Loan;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {

    private final ModelMapper modelMapper;

    public LoanMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public LoanDTO mapToDTO(Loan loan) {
        return modelMapper.map(loan, LoanDTO.class);
    }

    public Loan mapToEntity(LoanDTO loanDTO) {
        return modelMapper.map(loanDTO, Loan.class);
    }
}
