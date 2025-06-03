package com.eazybytes.accounts.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product_registration")
@Getter @Setter
public class ProductRegistration extends IdentifiableEntity {
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private User customer;

    private String productType; // SAVINGS, CARD, LOAN, ...
    private LocalDateTime registeredAt;
    private String status;      // PENDING, APPROVED, REJECTED
    private String approvedBy;
    private LocalDateTime approvedAt;
}
