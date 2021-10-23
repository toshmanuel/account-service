package com.digicore.dto.requests;


import lombok.Data;

@Data
public class DepositRequest {
    private String accountNumber;
    private Double amount;
}
