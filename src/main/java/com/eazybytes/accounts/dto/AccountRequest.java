package com.eazybytes.accounts.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AccountRequest {
	@NotBlank(message = "{notblank.account.type}")
	private String accountType;
    private String branchAddress;
}
