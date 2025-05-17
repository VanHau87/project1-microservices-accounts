package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.model.Account;

public interface AccountService {
	Account createAccount(CustomerDto dto);
}
