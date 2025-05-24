package com.eazybytes.accounts.repository.impl;

import java.util.List;

import com.eazybytes.accounts.model.Customer;
import com.eazybytes.accounts.repository.CustomUserRepository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository{
	
	private final EntityManager entityManager;
	@Override
	public Customer findByUniqueField(String fieldName, String value) {
		String jpql = "SELECT c FROM Customer c WHERE c." + fieldName + " = :value";
		List<Customer> results = entityManager
		        .createQuery(jpql, Customer.class)
		        .setParameter("value", value)
		        .getResultList();
		if (!results.isEmpty()) {
			return results.get(0);
		}
		return null;
	}

}
