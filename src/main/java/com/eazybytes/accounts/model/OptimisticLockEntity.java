package com.eazybytes.accounts.model;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @MappedSuperclass
public abstract class OptimisticLockEntity extends SoftDeletableEntity {
	@Version
	private Integer version;
}
