package com.eazybytes.accounts.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerDto {
	@NotBlank(message = "{notblank.users.name}")
	private String name;
	@NotBlank(message = "{notblank.users.email}")
    private String email;
	@NotBlank(message = "{notblank.users.mobileNumber}")
    private String mobileNumber;
}
