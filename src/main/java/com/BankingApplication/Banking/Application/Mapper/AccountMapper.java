package com.BankingApplication.Banking.Application.Mapper;

import com.BankingApplication.Banking.Application.DTO.AccountDTO;
import com.BankingApplication.Banking.Application.Entity.Account;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountMapper {
    private final ModelMapper modelMapper;

    public AccountMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AccountDTO mapToDTO(Account account) {
        return modelMapper.map(account, AccountDTO.class);
    }

    public Account mapToEntity(AccountDTO accountDTO) {
        return modelMapper.map(accountDTO, Account.class);
    }

    public List<AccountDTO> mapToDTOList(List<Account> accounts) {
        return accounts.stream()
                .map(account -> mapToDTO(account))  // Use mapToDTO to convert each account
                .collect(Collectors.toList());
    }
}
