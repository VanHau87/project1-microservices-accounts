package com.eazybytes.accounts.repository;

import com.eazybytes.accounts.model.Customer;

public interface CustomUserRepository {
	Customer findByUniqueField(String fieldName, String value);
}
