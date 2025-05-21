package com.eazybytes.accounts.utils;

import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.eazybytes.accounts.constants.AccountType;
import com.eazybytes.accounts.constants.BranchAddress;
import com.eazybytes.accounts.model.Account;
import com.eazybytes.accounts.model.Customer;

public class CommonUtils {
	
	private static final Random RANDOM = new Random();
	
	public static String generateAccountNumber(AccountType type) {
        return switch (type) {
            case SAVINGS -> randomDigits(8);
            case CHECKING -> randomDigits(10);
            case TERM_DEPOSIT -> groupDigits(3, 4);
            case CREDIT_CARD -> groupDigits(4, 4);
        };
    }
	private static String groupDigits(int groupCount, int digitsPerGroup) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < groupCount; i++) {
            for (int j = 0; j < digitsPerGroup; j++) {
                sb.append(RANDOM.nextInt(10));
            }
            if (i < groupCount - 1) sb.append("-");
        }
        return sb.toString();
    }
	private static String randomDigits(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(RANDOM.nextInt(10));
        }
        return sb.toString();
    }
	public static ResponseEntity<Map<String, String>> mappingErrorResult(BindingResult result) {
		Map<String, String> errors = result.getFieldErrors().stream()
				.collect(Collectors.toMap(
						FieldError -> FieldError.getField(), 
						FieldError -> FieldError.getDefaultMessage(),
						(existing, replacement) -> replacement));
		return ResponseEntity.badRequest().body(errors);
	}
	public static Account generateAccount(Customer customer) {
        return generateAccount(customer, AccountType.SAVINGS, BranchAddress.MAIN_BRANCH);
	}
	public static Account generateAccount(Customer customer, AccountType accountType, BranchAddress branchAddress) {
		Account account = new Account();
		account.setCustomer(customer);
		String accountNumberString = CommonUtils.generateAccountNumber(accountType);
        account.setAccountNumber(accountNumberString);
        account.setAccountType(accountType);
        account.setBranchAddress(branchAddress);
        return account;
	}
}
