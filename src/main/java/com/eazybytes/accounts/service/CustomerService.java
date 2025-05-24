package com.eazybytes.accounts.service;

import java.util.List;

import com.eazybytes.accounts.dto.CustomerRequest;
import com.eazybytes.accounts.dto.CustomerResponse;

public interface CustomerService {
    void createCustomer(CustomerRequest request);
    List<CustomerResponse> fetchUsers();
    boolean updateCustomer(CustomerRequest request);
}
