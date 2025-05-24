package com.eazybytes.accounts.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.accounts.constants.MessagesConstants;
import com.eazybytes.accounts.dto.CustomerRequest;
import com.eazybytes.accounts.dto.CustomerResponse;
import com.eazybytes.accounts.model.Customer;
import com.eazybytes.accounts.service.CustomerSearchService;
import com.eazybytes.accounts.service.CustomerService;
import com.eazybytes.accounts.utils.CommonUtils;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api.users}")
@RequiredArgsConstructor
public class CustomerController {
	
	private final CustomerService customerService;
	private final CustomerSearchService searchService;
	
	@PostMapping("create")
	public ResponseEntity<?> createUser(@Valid @RequestBody CustomerRequest dto, BindingResult result) {
		if(result.hasErrors()) {
    		return CommonUtils.mappingErrorResult(result);
    	}
		customerService.createCustomer(dto);
		return new ResponseEntity<>(MessagesConstants.MESSAGE_201, HttpStatus.CREATED);
    }
	@GetMapping
	public ResponseEntity<List<CustomerResponse>> fetchUsers() {
    	List<CustomerResponse> dtos = customerService.fetchUsers();
    	return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@PostMapping("{id}/accounts")
	public ResponseEntity<?> addAccountToUser() {
		return new ResponseEntity<>(MessagesConstants.MESSAGE_200, HttpStatus.OK);
	}
	@GetMapping("/search")
	  public ResponseEntity<Page<Customer>> search(
	      @RequestParam String q,
	      @RequestParam(defaultValue = "0") int page,
	      @RequestParam(defaultValue = "10") int size) {
	    return ResponseEntity.ok(searchService.search(q, page, size));
	  }
}
