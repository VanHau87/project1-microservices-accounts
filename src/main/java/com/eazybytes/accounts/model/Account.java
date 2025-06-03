package com.eazybytes.accounts.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.eazybytes.accounts.enums.AccountStatus;
import com.eazybytes.accounts.enums.AccountType;

import jakarta.persistence.Column;
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
@Getter @Setter
@SQLDelete(sql = "UPDATE account SET deleted = true, deleted_at = NOW(), deleted_by = ? WHERE id = ?")
@SQLRestriction("deleted = false")
@Table(name = "accounts")
public class Account extends OptimisticLockEntity {
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;
	
	@Column(nullable = false, length = 30, unique = true)
    private String accountNumber;
	
	@Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private AccountType accountType;
	
	@Column(nullable = false, length = 200)
    private String branchAddress;
	
	@Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;
	
	@Column(nullable = false, length = 3)
    private String currency; // "VND", "USD",...
	
	@Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AccountStatus status;
	
	@Column(nullable = false)
    private LocalDate openedDate;
	
	private LocalDate closedDate;
	
	@Column(length = 100)
    private String limitType; // Hạn mức nếu là credit (optional)
	
	@Column(precision = 18, scale = 2)
    private BigDecimal creditLimit; // Hạn mức tín dụng
    
}
