package com.digicore.services;

import com.digicore.dto.requests.DepositRequest;
import com.digicore.dto.requests.WithdrawRequest;
import com.digicore.dto.response.AccountInfo;
import com.digicore.dto.response.TransactionResponse;
import com.digicore.exceptions.AccountDoesNotExistException;
import com.digicore.exceptions.ExcessWithdrawnAmountException;
import com.digicore.exceptions.WrongPasswordException;
import com.digicore.model.Transaction;

import java.util.List;

public interface AccountService {

    TransactionResponse withdraw(WithdrawRequest request) throws AccountDoesNotExistException, ExcessWithdrawnAmountException;

    TransactionResponse deposit(DepositRequest request);

    AccountInfo getAccountInfo(String accountNumber, String password) throws AccountDoesNotExistException, WrongPasswordException;

    List<Transaction> getAllAccountTransactions(String accountNumber);


}
