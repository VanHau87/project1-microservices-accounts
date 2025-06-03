package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.UserRequest;
import com.eazybytes.accounts.dto.UserResponse;

public interface AccountService {
	void createAccount(UserRequest request);
	UserResponse fetchAccount(String mobilePhone);
	/**
	 * query flexible by fieldName
	 * @param fieldName
	 * @param value of fieldName
	 * @return
	 */
	UserResponse fetchAccountDetails(String fieldName, String value);
	boolean deleteAccount(UserRequest request);
}
