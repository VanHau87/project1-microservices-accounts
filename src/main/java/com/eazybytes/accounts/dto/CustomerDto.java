package com.eazybytes.accounts.dto;

import java.util.List;

import com.eazybytes.accounts.model.Customer;
import com.eazybytes.accounts.validation.UniqueField;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerDto {
	@NotBlank(message = "{notblank.users.name}")
	private String name;
	@NotBlank(message = "{notblank.users.email}")
	@UniqueField(entity = Customer.class, field = "email", message = "{exist.email}")
    private String email;
	@NotBlank(message = "{notblank.users.mobileNumber}")
	@UniqueField(entity = Customer.class, field = "mobileNumber", message = "{exist.mobile.number}")
    private String mobileNumber;
	
	private List<AccountDto> accounts;
}
