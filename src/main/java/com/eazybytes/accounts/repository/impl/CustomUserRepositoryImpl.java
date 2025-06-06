package com.eazybytes.accounts.repository.impl;

import com.eazybytes.accounts.model.User;
import com.eazybytes.accounts.repository.CustomUserRepository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository{
	
	private final EntityManager entityManager;
	@Override
	public User findByUniqueField(String fieldName, String value) {
		String jpql = "SELECT c FROM Customer c WHERE c." + fieldName + " = :value";
		return entityManager
		        .createQuery(jpql, User.class)
		        .setParameter("value", value)
		        .getResultStream()
		        .findFirst()
		        .orElse(null);
	}

}
