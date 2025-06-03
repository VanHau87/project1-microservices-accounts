package com.eazybytes.accounts.repository;

import com.eazybytes.accounts.model.User;

public interface CustomUserRepository {
	User findByUniqueField(String fieldName, String value);
}
