package com.eazybytes.accounts.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.eazybytes.accounts.constants.AccountConstant;
import com.eazybytes.accounts.dto.AccountDto;
import com.eazybytes.accounts.dto.CustomerDto;
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
	
	private final AccountRepository accountRepository;
	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;
    private final AccountMapper accountMapper;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void createAccount(CustomerDto dto) {
		Customer target = new Customer();
		customerMapper.mapCustomerFromDto(dto, target);
        Customer customer = customerRepository.save(target);
        Account account = generateAccount(customer);
        accountRepository.save(account);
	}
	@Override
	public CustomerDto fetchAccount(String mobilePhone) {
		Customer customer = customerRepository.findByMobileNumber(mobilePhone)
				.orElseThrow(() -> new EntityNotFoundException("Mobile phone", mobilePhone));
		CustomerDto dto = new CustomerDto();
		customerMapper.mapCustomerToDto(customer, dto);
		List<AccountDto> accounts = new ArrayList<>();
        for (Account account : customer.getAccounts()) {
            AccountDto accountDto = new AccountDto();
            accountMapper.mapAccountToDto(account, accountDto);
            accounts.add(accountDto);
        }
        dto.setAccounts(accounts);
		return dto;
	}
	@Override
	public List<CustomerDto> fetchAccountDetails(String fieldName, String value) {
		String jpql = "SELECT c FROM Customer c WHERE c." + fieldName + " = :value";
		List<Customer> results = entityManager
		        .createQuery(jpql, Customer.class)
		        .setParameter("value", value)
		        .getResultList();
		if (results.isEmpty()) {
	        throw new EntityNotFoundException(fieldName, value);
	    }
		List<CustomerDto> dtos = new ArrayList<>();
        for (Customer customer : results) {
            CustomerDto dto = new CustomerDto();
            customerMapper.mapCustomerToDto(customer, dto);
            dtos.add(dto);
        }
		return dtos;
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
