package com.digicore.controller;

import com.digicore.dto.requests.AccountRegistrationRequest;
import com.digicore.dto.requests.AuthenticationRequest;
import com.digicore.exceptions.AccountAlreadyExistsException;
import com.digicore.exceptions.InsufficientInitialDepositException;
import com.digicore.exceptions.InvalidAccountNameException;
import com.digicore.model.Account;
import com.digicore.services.AccountAuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value="/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AccountAuthService accountAuthService;


    @PostMapping(value="/create_account")
    public ResponseEntity<?> register(@RequestBody AccountRegistrationRequest registerRequest) {
        try {
            return new ResponseEntity<>("your account is: " + accountAuthService.createAccount(registerRequest), HttpStatus.OK);
        } catch (AccountAlreadyExistsException | InvalidAccountNameException | InsufficientInitialDepositException e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest registerRequest){
        try {
            return new ResponseEntity<>(accountAuthService.login(registerRequest), HttpStatus.ACCEPTED);
        }catch (BadCredentialsException e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.FORBIDDEN);
        }
    }

}
