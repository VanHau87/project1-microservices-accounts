package com.eazybytes.accounts.service;

import java.util.List;

import com.eazybytes.accounts.dto.CustomerRequest;
import com.eazybytes.accounts.dto.CustomerResponse;

public interface AccountService {
	void createAccount(CustomerRequest request);
	CustomerResponse fetchAccount(String mobilePhone);
	/**
	 * query flexible by fieldName
	 * @param fieldName
	 * @param value of fieldName
	 * @return
	 */
	List<CustomerResponse> fetchAccountDetails(String fieldName, String value);
}
