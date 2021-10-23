package com.digicore.repository;

import com.digicore.model.Account;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountRepositoryImpl implements AccountRepository {

    private final Map<String, Account> accounts;

    public AccountRepositoryImpl() {
        this.accounts = new HashMap<>();
    }

    @Override
    public void save(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    @Override
    public Account findByAccountNumber(String accountNumber) {
        return accounts.get(accountNumber);
    }

    @Override
    public void deleteAccount(String accountNumber) {
        accounts.remove(accountNumber);
    }

    @Override
    public Set<String> getAllAccountNumber() {
        return accounts.keySet();
    }

    @Override
    public String findAccountNumberByName(String accountName) {
        for (Account account : accounts.values()){
            if (account.getAccountName().equalsIgnoreCase(accountName)){
                return account.getAccountName();
            }
        }
        return null;
    }



}
