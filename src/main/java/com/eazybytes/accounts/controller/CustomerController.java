package com.eazybytes.accounts.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.accounts.constants.CustomerConstant;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.service.CustomerService;
import com.eazybytes.accounts.utils.CommonUtils;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api.users}")
@RequiredArgsConstructor
public class CustomerController {
	
	private final CustomerService customerService;
	
	@PostMapping("create")
	public ResponseEntity<?> createUser(@Valid @RequestBody CustomerDto dto, BindingResult result) {
		if(result.hasErrors()) {
    		return CommonUtils.mappingErrorResult(result);
    	}
		customerService.createCustomer(dto);
		return new ResponseEntity<>(CustomerConstant.MESSAGE_201, HttpStatus.CREATED);
    }
	@GetMapping
	public ResponseEntity<List<CustomerDto>> fetchUsers() {
    	List<CustomerDto> dtos = customerService.fetchUsers();
    	return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	/*
	@PostMapping("{id}/accounts")
	public ResponseEntity<?> addAccountToUser() {
		return new ResponseEntity<>(CustomerConstant.MESSAGE_200, HttpStatus.OK);
	}
	*/
}
