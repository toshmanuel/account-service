package com.digicore.controller;

import com.digicore.dto.requests.DepositRequest;
import com.digicore.dto.requests.WithdrawRequest;
import com.digicore.dto.response.TransactionResponse;
import com.digicore.exceptions.AccountDoesNotExistException;
import com.digicore.exceptions.ExcessWithdrawnAmountException;
import com.digicore.exceptions.WrongPasswordException;
import com.digicore.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountTransactionController {
    private final AccountService accountService;

    @PostMapping("/withdrawal")
    public ResponseEntity<?> withdraw(@RequestBody WithdrawRequest request){
        try {
            return new ResponseEntity<>(accountService.withdraw(request), HttpStatus.OK);
        } catch (AccountDoesNotExistException | ExcessWithdrawnAmountException | IllegalArgumentException e) {
            return new ResponseEntity<>(new TransactionResponse(false, e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody DepositRequest request){
        try{
            return new ResponseEntity<>(accountService.deposit(request), HttpStatus.OK);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<>(new TransactionResponse(false, e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/account_info/{accountNumber}")
    public ResponseEntity<?> getAccountInfo(@PathVariable String accountNumber, @RequestParam String password){
        try {
            return new ResponseEntity<>(accountService.getAccountInfo(accountNumber, password), HttpStatus.OK);
        } catch (AccountDoesNotExistException | WrongPasswordException e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/account_statement/{accountNumber}")
    public ResponseEntity<?> getAccountStatement(@PathVariable String accountNumber){
        return new ResponseEntity<>(accountService.getAllAccountTransactions(accountNumber), HttpStatus.OK);
    }
}
