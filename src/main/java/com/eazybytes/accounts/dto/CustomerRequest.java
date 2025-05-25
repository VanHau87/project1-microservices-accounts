package com.eazybytes.accounts.dto;

import java.util.List;

import com.eazybytes.accounts.model.Customer;
import com.eazybytes.accounts.validation.UniqueField;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@UniqueField(entity = Customer.class, fields = {"email", "mobileNumber"})
public class CustomerRequest {
	private Long id;
	@NotBlank(message = "{notblank.users.name}")
	private String name;
	@NotBlank(message = "{notblank.users.email}")
    private String email;
	@NotBlank(message = "{notblank.users.mobileNumber}")
    private String mobileNumber;
	private List<AccountRequest> accounts;
}
