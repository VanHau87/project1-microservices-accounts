package com.eazybytes.accounts.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity // activate @PreAuthorize
public class SecurityConfig {
	
	private final CustomUserDetailsService userDetailsService;
    
    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Bean PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // temporary disable CSRF
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/public/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
    // In-memory
    @Bean InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder encoder) {
        return new InMemoryUserDetailsManager(
            User.withUsername("testadmin")
                .password(encoder.encode("test123am"))
                .roles("ADMIN")
                .build(),
            User.withUsername("testuser")
                .password(passwordEncoder().encode("test321us"))
                .roles("USER")
                .build()  
        );
    }
    // Database
    @Bean DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    // InMemory Provider
    @Bean DaoAuthenticationProvider inMemoryAuthProvider(InMemoryUserDetailsManager inMemoryUserDetailsManager, PasswordEncoder encoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(inMemoryUserDetailsManager);
        provider.setPasswordEncoder(encoder);
        return provider;
    }
    @Bean AuthenticationManager authenticationManager(DaoAuthenticationProvider authenticationProvider,
            DaoAuthenticationProvider inMemoryAuthProvider) throws Exception {
    	return new ProviderManager(
    	        List.of(inMemoryAuthProvider, authenticationProvider)
    	    );
    }
    
}
