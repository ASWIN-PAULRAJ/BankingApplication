package com.BankingApplication.Banking.Application.Mapper;

import com.BankingApplication.Banking.Application.DTO.TransactionDTO;
import com.BankingApplication.Banking.Application.Entity.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    private final ModelMapper modelMapper;

    public TransactionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public  TransactionDTO mapToDTO(Transaction transaction) {
        return modelMapper.map(transaction, TransactionDTO.class);
    }

    public Transaction mapToEntity(TransactionDTO transactionDTO) {
        return modelMapper.map(transactionDTO, Transaction.class);
    }
}
