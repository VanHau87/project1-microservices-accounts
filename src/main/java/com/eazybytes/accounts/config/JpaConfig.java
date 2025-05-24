package com.eazybytes.accounts.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.eazybytes.accounts.repository.SoftDeleteRepositoryImpl;

@Configuration
@EnableJpaRepositories(
			basePackages = "com.eazybytes.accounts.repository",
			repositoryBaseClass = SoftDeleteRepositoryImpl.class
		)
public class JpaConfig {

}
