package com.eazybytes.accounts.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import com.eazybytes.accounts.context.CurrentUserContext;

@Configuration
public class AuditorConfig {
	
	@Bean AuditorAware<String> auditorAware() {
        return () -> Optional.of(CurrentUserContext.get());
    }
}
