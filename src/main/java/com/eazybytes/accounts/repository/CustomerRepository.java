package com.eazybytes.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eazybytes.accounts.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomUserRepository {
	Optional<Customer> findByMobileNumber(String mobileNumber);
	Optional<Customer> findById(Long id);
}
