package com.eazybytes.accounts.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserResponse {
	private String username;
    private String email;
    private String mobileNumber;
	private List<AccountResponse> accounts;
}
