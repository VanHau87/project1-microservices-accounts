package com.eazybytes.accounts.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.eazybytes.accounts.constants.AccountType;
import com.eazybytes.accounts.constants.BranchAddress;
import com.eazybytes.accounts.dto.AccountResponse;
import com.eazybytes.accounts.dto.CustomerRequest;
import com.eazybytes.accounts.dto.CustomerResponse;
import com.eazybytes.accounts.exceptions.EntityNotFoundException;
import com.eazybytes.accounts.model.Account;
import com.eazybytes.accounts.model.Customer;
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
	public List<CustomerResponse> fetchAccountDetails(String fieldName, String value) {
		String jpql = "SELECT c FROM Customer c WHERE c." + fieldName + " = :value";
		List<Customer> results = entityManager
		        .createQuery(jpql, Customer.class)
		        .setParameter("value", value)
		        .getResultList();
		if (results.isEmpty()) {
	        throw new EntityNotFoundException(fieldName, value);
	    }
		List<CustomerResponse> responses = new ArrayList<>();
        for (Customer customer : results) {
            CustomerResponse response = new CustomerResponse();
            customerMapper.mapCustomerToResponse(customer, response);
            responses.add(response);
        }
		return responses;
	}
}
