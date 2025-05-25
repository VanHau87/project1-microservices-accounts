package com.eazybytes.accounts.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.eazybytes.accounts.constants.BranchAddress;
import com.eazybytes.accounts.dto.AccountRequest;
import com.eazybytes.accounts.dto.AccountResponse;
import com.eazybytes.accounts.dto.CustomerRequest;
import com.eazybytes.accounts.dto.CustomerResponse;
import com.eazybytes.accounts.enums.AccountType;
import com.eazybytes.accounts.exceptions.EntityNotFoundException;
import com.eazybytes.accounts.model.Account;
import com.eazybytes.accounts.model.Customer;
import com.eazybytes.accounts.repository.AccountRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.AccountService;
import com.eazybytes.accounts.utils.AccountMapper;
import com.eazybytes.accounts.utils.CommonUtils;
import com.eazybytes.accounts.utils.CustomerMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
	
	private final CustomerRepository customerRepository;
	private final AccountRepository accountRepository;
	private final CustomerMapper customerMapper;
    private final AccountMapper accountMapper;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void createAccount(CustomerRequest request) {
		Customer target = new Customer();
		customerMapper.mapCustomerFromRequest(request, target);
        List<Account> accounts = request.getAccounts().stream().map(ar -> {
            AccountType type = AccountType.valueOf(ar.getAccountType());
            BranchAddress branchAddress = BranchAddress.valueOf(ar.getBranchAddress());
            return CommonUtils.generateAccount(target, type, branchAddress);
        }).toList();
        target.setAccounts(accounts);
        customerRepository.save(target);
	}
	@Override
	public CustomerResponse fetchAccount(String mobilePhone) {
		Customer customer = customerRepository.findByMobileNumber(mobilePhone)
				.orElseThrow(() -> new EntityNotFoundException("Mobile phone", mobilePhone));
		CustomerResponse response = new CustomerResponse();
		customerMapper.mapCustomerToResponse(customer, response);
		List<AccountResponse> accounts = new ArrayList<>();
        for (Account account : customer.getAccounts()) {
            AccountResponse accountResponse = new AccountResponse();
            accountMapper.mapAccountToResponse(account, accountResponse);
            accounts.add(accountResponse);
        }
        response.setAccounts(accounts);
		return response;
	}
	@Override
	public CustomerResponse fetchAccountDetails(String fieldName, String value) {
		Customer customer = customerRepository.findByUniqueField(fieldName, value);
		if (customer == null) {
	        throw new EntityNotFoundException(fieldName, value);
	    }
		CustomerResponse response = new CustomerResponse();
		customerMapper.mapCustomerToResponse(customer, response);
		return response;
	}
	@Override
	public boolean deleteAccount(CustomerRequest request) {
		String mobilePhone = request.getMobileNumber();
		Customer customer = customerRepository.findByMobileNumber(mobilePhone)
				.orElseThrow(() -> new EntityNotFoundException("Mobile phone", mobilePhone));
        List<AccountRequest> deletedAccounts = request.getAccounts();
        List<Account> accounts = customer.getAccounts();
        if (accounts != null && !accounts.isEmpty()) {
            for (Account account : accounts) {
                String accountNumber = account.getAccountNumber();
                if (deletedAccounts.stream().anyMatch(ar -> ar.getAccountNumber().equals(accountNumber))) {
                    accountRepository.delete(account);
                }
            }
		}
        return true;
	}
}
