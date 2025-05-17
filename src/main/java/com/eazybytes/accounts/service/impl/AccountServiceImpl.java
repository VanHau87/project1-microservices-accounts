package com.eazybytes.accounts.service.impl;

import org.springframework.stereotype.Service;

import com.eazybytes.accounts.constants.AccountConstant;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.model.Account;
import com.eazybytes.accounts.model.Customer;
import com.eazybytes.accounts.repository.AccountRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.AccountService;
import com.eazybytes.accounts.utils.CommonUtils;
import com.eazybytes.accounts.utils.CustomerMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
	
	private final AccountRepository accountRepository;
	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;
	@Override
	public Account createAccount(CustomerDto dto) {
		Customer target = new Customer();
		customerMapper.mapCustomerFromDto(dto, target);
        Customer customer = customerRepository.save(target);
        Account account = generateAccount(customer);
        Account savedAccount = accountRepository.save(account);
        return savedAccount;
	}
	private Account generateAccount(Customer customer) {
		Account account = new Account();
		account.setCustomer(customer);
		String accountNumberString = CommonUtils.generateAccountNumber();
        account.setAccountNumber(accountNumberString);
        account.setAccountType(AccountConstant.SAVINGS);
        account.setBranchAddress(AccountConstant.ADDRESS);
        return account;
	}
}
