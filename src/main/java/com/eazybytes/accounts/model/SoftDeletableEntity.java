package com.eazybytes.accounts.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @MappedSuperclass
public abstract class SoftDeletableEntity extends AuditableEntity {
	@Column(nullable = false)
	private Boolean deleted = false;
	@Column
	private LocalDateTime deletedAt;
    @Column
	private String deletedBy;
}
