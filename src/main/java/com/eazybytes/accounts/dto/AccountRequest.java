package com.eazybytes.accounts.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AccountRequest {
	private String accountNumber;
	@NotBlank(message = "{notblank.account.type}")
	private String accountType;
    private String branchAddress;
}
