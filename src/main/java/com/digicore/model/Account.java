package com.digicore.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String accountNumber;
    private String accountName;
    private String accountPassword;
    private Double accountBalance;
    private List<Transaction> transactions;

    public void addTransaction(Transaction transaction){
        if(transactions == null){
            transactions = new ArrayList<>();
        }
        transactions.add(transaction);
    }
}
