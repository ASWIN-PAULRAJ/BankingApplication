package com.BankingApplication.Banking.Application.Repositary;

import com.BankingApplication.Banking.Application.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {

    List<Account> findAllByUser_UserId(Long userId);
}
