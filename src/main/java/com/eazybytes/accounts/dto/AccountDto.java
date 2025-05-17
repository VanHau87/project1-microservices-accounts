package com.eazybytes.accounts.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AccountDto {
	private String accountNumber;
	@NotBlank(message = "{notblank.account.type}")
    private String accountType;
	@NotBlank(message = "{account.number.notblank}")
    private String branchAddress;
}
