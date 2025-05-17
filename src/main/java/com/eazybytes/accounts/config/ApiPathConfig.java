package com.eazybytes.accounts.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "api")
@PropertySource("classpath:api_paths.properties")
@Getter @Setter
public class ApiPathConfig {
	private String users;
	private String accounts;
}
