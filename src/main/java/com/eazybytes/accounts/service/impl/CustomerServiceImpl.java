package com.eazybytes.accounts.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eazybytes.accounts.dto.AccountDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.model.Account;
import com.eazybytes.accounts.model.Customer;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.CustomerService;
import com.eazybytes.accounts.utils.AccountMapper;
import com.eazybytes.accounts.utils.CustomerMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;
	private final AccountMapper accountMapper;
	
	@Override
	public void createCustomer(CustomerDto dto) {
		Customer customer = new Customer();
		customerMapper.mapCustomerFromDto(dto, customer);
		customerRepository.save(customer);
	}

	@Override
	public List<CustomerDto> fetchUsers() {
		List<Customer> customers = customerRepository.findAll();
	    if (customers.isEmpty()) {
	        return List.of();
	    }
	    return customers.stream().map(customer -> {
	        CustomerDto dto = new CustomerDto();
	        customerMapper.mapCustomerToDto(customer, dto);

	        List<Account> accounts = customer.getAccounts();
	        if (accounts != null && !accounts.isEmpty()) {
	            List<AccountDto> accountDtos = accounts.stream().map(account -> {
	                AccountDto accountDto = new AccountDto();
	                accountMapper.mapAccountToDto(account, accountDto);
	                return accountDto;
	            }).toList();
	            dto.setAccounts(accountDtos);
	        }

	        return dto;
	    }).toList();
	}

}
