package com.eazybytes.accounts.service;

import java.util.List;

import com.eazybytes.accounts.dto.CustomerDto;

public interface AccountService {
	void createAccount(CustomerDto dto);
	CustomerDto fetchAccount(String mobilePhone);
	/**
	 * 
	 * @param fieldName
	 * @param value of fieldName must be unique in database
	 * @return
	 */
	List<CustomerDto> fetchAccountDetails(String fieldName, String value);
}
