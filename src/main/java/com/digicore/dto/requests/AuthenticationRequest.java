package com.digicore.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {
    private String accountNumber;
    private String accountPassword;
}
