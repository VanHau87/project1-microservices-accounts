package com.eazybytes.accounts.controller;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.accounts.constants.AccountConstant;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.model.Account;
import com.eazybytes.accounts.service.AccountService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api.accounts}")
@RequiredArgsConstructor
public class AccountsController {
	
	private final AccountService accountService;
	
	@PostMapping("create")
	public ResponseEntity<?> createAccount(@Valid @RequestBody CustomerDto dto, BindingResult result) {
    	if(result.hasErrors()) {
    		Map<String, String> errors = result.getFieldErrors().stream()
    				.collect(Collectors.toMap(
    						FieldError -> FieldError.getField(), 
    						FieldError -> FieldError.getDefaultMessage(),
    						(existing, replacement) -> replacement));
    		return ResponseEntity.badRequest().body(errors);
    	}
        
        Account account = accountService.createAccount(dto);
		return new ResponseEntity<>(account, HttpStatus.CREATED);
	}
}
