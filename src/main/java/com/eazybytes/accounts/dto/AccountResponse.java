package com.eazybytes.accounts.dto;

import lombok.Data;

@Data
public class AccountResponse {
	private String accountNumber;
	private String accountType;
    private String branchAddress;
}
