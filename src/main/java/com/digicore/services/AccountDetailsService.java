package com.digicore.services;

import com.digicore.model.Account;
import com.digicore.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@AllArgsConstructor
@Service
public class AccountDetailsService implements UserDetailsService{
    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String accountNumber) throws UsernameNotFoundException {
        Account account = accountRepository.findByAccountNumber(accountNumber);

        return new User(account.getAccountNumber(), account.getAccountPassword(), new ArrayList<>());
    }
}
