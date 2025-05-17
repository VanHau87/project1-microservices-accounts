package com.eazybytes.accounts.validation;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationErrorResponse {
	private Map<String, String> errors;
}
