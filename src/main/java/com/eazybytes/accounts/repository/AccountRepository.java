package com.eazybytes.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eazybytes.accounts.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
