package com.eazybytes.accounts.dto;

import com.eazybytes.accounts.enums.CustomerStatus;
import com.eazybytes.accounts.model.User;
import com.eazybytes.accounts.validation.UniqueField;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@UniqueField(entity = User.class, fields = {"email", "mobileNumber"})
public class UserRequest {
	@NotBlank(message = "{notblank.users.name}")
	private String username;
	@NotBlank
	private String fullName;
	@NotBlank(message = "{notblank.users.email}")
	@Email(message = "Email invalid")
    private String email;
	@NotBlank(message = "{notblank.users.mobileNumber}")
    private String mobileNumber;
	@NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
	private String password;
	@NotBlank
	private String customerType;
	private CustomerStatus status;
}
