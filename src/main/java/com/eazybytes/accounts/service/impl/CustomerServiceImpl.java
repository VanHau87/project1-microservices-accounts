package com.eazybytes.accounts.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.eazybytes.accounts.dto.CustomerRequest;
import com.eazybytes.accounts.dto.CustomerResponse;
import com.eazybytes.accounts.model.Customer;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.CustomerService;
import com.eazybytes.accounts.utils.CustomerMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;
	//private final AccountMapper accountMapper;
	
	@Override
	public void createCustomer(CustomerRequest dto) {
		Customer customer = new Customer();
		customerMapper.mapCustomerFromRequest(dto, customer);
		customerRepository.save(customer);
	}

	@Override
	public List<CustomerResponse> fetchUsers() {
		List<Customer> customers = customerRepository.findAll();
	    if (customers.isEmpty()) {
	        return List.of();
	    }
        List<CustomerResponse> responses = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerResponse response = new CustomerResponse();
            customerMapper.mapCustomerToResponse(customer, response);
            responses.add(response);
        }
        return responses;
	}

	@Override
	public CustomerRequest addAccountToUser() {
		// TODO Auto-generated method stub
		return null;
	}

}
