package com.eazybytes.accounts.validation;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.eazybytes.accounts.model.Customer;
import com.eazybytes.accounts.repository.CustomerRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UniqueMobileNumberValidator implements ConstraintValidator<UniqueMobileNumber, String> {
	
	private final CustomerRepository customerRepository;
	
	@Override
    public boolean isValid(String mobileNumber, ConstraintValidatorContext context) {
        if (mobileNumber == null || mobileNumber.isBlank()) {
            return true; // Let @NotBlank handle this
        }
        Optional<Customer> existingCustomer = customerRepository.findByMobileNumber(mobileNumber);
        return !existingCustomer.isPresent();
    }
}
