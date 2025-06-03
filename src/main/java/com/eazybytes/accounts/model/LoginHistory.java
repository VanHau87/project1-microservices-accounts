package com.eazybytes.accounts.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "login_history")
@Getter @Setter
public class LoginHistory extends IdentifiableEntity {
	private Long customerId;
    private String username;
    private LocalDateTime loginAt;
    private String status; // SUCCESS, FAIL
    private String ipAddress;
    private String deviceInfo;
}
