package com.digicore.services;

import com.digicore.dto.requests.DepositRequest;
import com.digicore.dto.requests.WithdrawRequest;
import com.digicore.dto.response.AccountInfo;
import com.digicore.dto.response.TransactionResponse;
import com.digicore.exceptions.AccountDoesNotExistException;
import com.digicore.exceptions.ExcessWithdrawnAmountException;
import com.digicore.exceptions.WrongPasswordException;
import com.digicore.model.Account;
import com.digicore.model.Transaction;
import com.digicore.model.TransactionType;
import com.digicore.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;

    @Override
    public TransactionResponse withdraw(WithdrawRequest request) throws AccountDoesNotExistException, ExcessWithdrawnAmountException {
        if(request.getWithdrawnAmount() < 1.0){
            throw new IllegalArgumentException("Can not withdraw below #1.00");
        }

        Account account = accountRepository.findByAccountNumber(request.getAccountNumber());
        if(account == null){
            throw new AccountDoesNotExistException("This account does not exist");
        }

        if(account.getAccountBalance() - request.getWithdrawnAmount() < 500){
            throw new ExcessWithdrawnAmountException("Cannot withdraw amount because a limit " +
                    "of #500 should be left in the account");
        }

        Double newBalance = account.getAccountBalance() - request.getWithdrawnAmount();
        Transaction transaction = Transaction.builder()
                .transactionDate(LocalDate.now())
                .transactionType(TransactionType.WITHDRAWAL.name())
                .narration("transaction successful")
                .amount(request.getWithdrawnAmount())
                .accountBalance(newBalance)
                .build();

        account.addTransaction(transaction);
        account.setAccountBalance(newBalance);
//        accountRepository.save(account);
        return new TransactionResponse(true, "1000 successfully withdrawn" +
                " new balance is: #" + account.getAccountBalance());
    }

    @Override
    public TransactionResponse deposit(DepositRequest request) {
        if(request.getAmount() < 1.0 || request.getAmount() > 1000000){
            throw new IllegalArgumentException("Cannot deposit below #1.00 or above #1,000,000");
        }

        Account account = accountRepository.findByAccountNumber(request.getAccountNumber());
        Double newBalance = account.getAccountBalance() + request.getAmount();
        Transaction transaction = Transaction.builder()
                .transactionDate(LocalDate.now())
                .transactionType(TransactionType.DEPOSIT.name())
                .narration("transaction successful")
                .amount(request.getAmount())
                .accountBalance(newBalance)
                .build();

        account.addTransaction(transaction);
        account.setAccountBalance(newBalance);
        return new TransactionResponse(true, "transaction successful");
    }

    @Override
    public AccountInfo getAccountInfo(String accountNumber, String password) throws AccountDoesNotExistException, WrongPasswordException {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new AccountDoesNotExistException("This account is does not exist");
        }
        if (!password.equals(account.getAccountPassword())) {
            throw new WrongPasswordException("Wrong password");
        }

        return AccountInfo.builder()
                .balance(account.getAccountBalance())
                .accountNumber(account.getAccountNumber())
                .accountName(account.getAccountName())
                .build();
    }

    @Override
    public List<Transaction> getAllAccountTransactions(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        return account.getTransactions();
    }

}
