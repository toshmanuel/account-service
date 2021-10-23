package com.digicore.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountInfo {
    private String accountName;
    private String accountNumber;
    private Double balance;
}
