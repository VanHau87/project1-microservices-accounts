package com.eazybytes.accounts.model;

import java.time.LocalDateTime;

import com.eazybytes.accounts.enums.AccountStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "account_status_history")
@Getter @Setter
public class AccountStatusHistory extends IdentifiableEntity {
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
	private Account account;
	
	@Enumerated(EnumType.STRING)
    private AccountStatus oldStatus;

    @Enumerated(EnumType.STRING)
    private AccountStatus newStatus;

    private String changedBy; // ai thao tác
    private LocalDateTime changedAt;

    private String reason;    // Lý do nếu có
}
