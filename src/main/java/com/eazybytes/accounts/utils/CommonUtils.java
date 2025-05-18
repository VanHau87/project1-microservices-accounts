package com.eazybytes.accounts.utils;

import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class CommonUtils {
	
	private static final Random RANDOM = new Random();
	
	public static String generateAccountNumber() {
        StringBuilder sb = new StringBuilder();
        for (int group = 0; group < 4; group++) {
            for (int i = 0; i < 4; i++) {
                sb.append(RANDOM.nextInt(10));
            }
            if (group < 3) {
                sb.append("-");
            }
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
}
