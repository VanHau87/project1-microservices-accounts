package com.eazybytes.accounts.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity extends IdentifiableEntity {
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdAt;
	
	@Column
	@LastModifiedDate
    private LocalDateTime updatedAt;
	
	@CreatedBy
    @Column(updatable = false)
    private String createdBy;
	
	@LastModifiedBy
    @Column
    private String updatedBy;
}
