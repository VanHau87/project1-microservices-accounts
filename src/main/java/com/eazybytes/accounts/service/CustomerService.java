package com.eazybytes.accounts.service;

import java.util.List;

import com.eazybytes.accounts.dto.CustomerDto;

public interface CustomerService {
    void createCustomer(CustomerDto dto);
    List<CustomerDto> fetchUsers();
}
