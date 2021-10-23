package com.digicore.services;

import com.digicore.dto.requests.AccountRegistrationRequest;
import com.digicore.dto.requests.AuthenticationRequest;
import com.digicore.exceptions.AccountAlreadyExistsException;
import com.digicore.exceptions.InsufficientInitialDepositException;
import com.digicore.exceptions.InvalidAccountNameException;
import com.digicore.model.Account;
import com.digicore.repository.AccountRepository;
import com.digicore.security.jwt.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Set;

@Service
@AllArgsConstructor
public class AccountAuthService {

    private final AccountRepository accountRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtils;

    private final AccountDetailsService accountDetailsService;


    public String createAccount(AccountRegistrationRequest accountRegistrationRequest) throws AccountAlreadyExistsException, InvalidAccountNameException, InsufficientInitialDepositException {
        if(accountRegistrationRequest.getAccountName() == null || accountRegistrationRequest.getAccountName().isEmpty()){
            throw new InvalidAccountNameException("Account name cannot be empty");
        }
        if (accountRegistrationRequest.getInitialDeposit() < 500){
            throw new InsufficientInitialDepositException("Initial deposit can not be less than 500.00");
        }
        String existingAccountName = accountRepository.findAccountNumberByName(accountRegistrationRequest.getAccountName());
        if(existingAccountName != null){
            throw new AccountAlreadyExistsException("This account name already exist, " +
                    "register with a different name");
        }

        Account account = Account.builder()
                .accountNumber(accountNumberGenerator())
                .accountName(accountRegistrationRequest.getAccountName())
                .accountPassword((accountRegistrationRequest.getAccountPassword()))
                .accountBalance(accountRegistrationRequest.getInitialDeposit())
                .build();
        accountRepository.save(account);
        return account.getAccountNumber();
    }

    private String accountNumberGenerator(){
        SecureRandom rand = new SecureRandom();
        String accountNumber = (10000 + rand.nextInt(99999)) + "" + (10000 + rand.nextInt(99999));
        while (accountRepository.getAllAccountNumber().contains(accountNumber)){
            accountNumber = (10000 + rand.nextInt(99999)) + "" + (10000 + rand.nextInt(99999));
        }
        return accountNumber;
    }

    public String login(AuthenticationRequest authenticationRequest) throws BadCredentialsException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getAccountNumber(),
                authenticationRequest.getAccountPassword()));

        UserDetails userDetails = accountDetailsService.loadUserByUsername(authenticationRequest.getAccountNumber());

        return jwtUtils.generateToken(userDetails);
    }
}
