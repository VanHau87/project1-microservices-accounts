package com.eazybytes.accounts.service;

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
	CustomerResponse fetchAccountDetails(String fieldName, String value);
	boolean deleteAccount(CustomerRequest request);
}
