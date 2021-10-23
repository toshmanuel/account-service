package com.digicore.repository;

import com.digicore.model.Account;

import java.util.Set;

public interface AccountRepository {

    void save(Account account);

    Account findByAccountNumber(String accountNumber);

    void deleteAccount(String accountNumber);

    Set<String> getAllAccountNumber();

    String findAccountNumberByName(String accountName);
}
