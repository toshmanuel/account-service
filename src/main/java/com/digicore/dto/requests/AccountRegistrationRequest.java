package com.digicore.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class AccountRegistrationRequest {
    private String accountName;
    private String accountPassword;
    private Double initialDeposit;
}
