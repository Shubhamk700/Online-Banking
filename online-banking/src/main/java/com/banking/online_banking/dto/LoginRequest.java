package com.banking.online_banking.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
