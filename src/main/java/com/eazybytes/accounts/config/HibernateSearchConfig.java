package com.eazybytes.accounts.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateSearchConfig {
	
	@Bean ApplicationRunner massIndexing(HibernateSearchIndexer indexer) {
		return args -> {
            indexer.startMassIndexer();
        };
	}
}
