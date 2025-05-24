package com.eazybytes.accounts.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.accounts.constants.MessagesConstants;
import com.eazybytes.accounts.dto.CustomerRequest;
import com.eazybytes.accounts.dto.CustomerResponse;
import com.eazybytes.accounts.dto.FieldQueryRequest;
import com.eazybytes.accounts.service.AccountService;
import com.eazybytes.accounts.utils.CommonUtils;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api.accounts}")
@RequiredArgsConstructor
public class AccountsController {
	
	private final AccountService accountService;
	
	@PostMapping("create")
	public ResponseEntity<?> createAccount(@Valid @RequestBody CustomerRequest dto, BindingResult result) {
    	if(result.hasErrors()) {
    		return CommonUtils.mappingErrorResult(result);
    	}
        accountService.createAccount(dto);
		return new ResponseEntity<>(MessagesConstants.MESSAGE_201, HttpStatus.CREATED);
	}
	@GetMapping("fetch/{mobilePhone}")
	public ResponseEntity<CustomerResponse> fetchAccount(@PathVariable String mobilePhone) {
        CustomerResponse dto = accountService.fetchAccount(mobilePhone);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
	@PostMapping("fetch")
	public ResponseEntity<List<CustomerResponse>> fetchAccountDetails(@RequestBody @Valid FieldQueryRequest request) {
		List<CustomerResponse> dtos = accountService.fetchAccountDetails(request.getFieldName(), request.getValue());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	public ResponseEntity<String> updateAccountDetails(@RequestBody CustomerRequest dto) {
        return new ResponseEntity<>(MessagesConstants.MESSAGE_200, HttpStatus.OK);
    }
}
