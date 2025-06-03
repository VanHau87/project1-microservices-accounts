package com.eazybytes.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eazybytes.accounts.model.User;

public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {
	Optional<User> findByMobileNumber(String mobileNumber);
	Optional<User> findById(Long id);
	Optional<User> findByUsernameOrEmail(String username, String email);
}
