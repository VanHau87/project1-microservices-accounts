package com.eazybytes.accounts.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
@SQLDelete(sql = "UPDATE account SET deleted = true, deleted_at = NOW(), deleted_by = ? WHERE id = ?")
@SQLRestriction("deleted = false")
public class Account extends OptimisticLockEntity {
    private String accountNumber;
    private String accountType;
    private String branchAddress;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}
