package com.eazybytes.accounts.repository;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.eazybytes.accounts.config.SpringContext;
import com.eazybytes.accounts.model.SoftDeletableEntity;

import jakarta.persistence.EntityManager;

public class SoftDeleteRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
		implements BaseRepository<T, ID> {

	public SoftDeleteRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
	}

	public void delete(T entity) {
		if (entity instanceof SoftDeletableEntity) {
			SoftDeletableEntity softDeletableEntity = (SoftDeletableEntity) entity;
			softDeletableEntity.setDeleted(true);
			softDeletableEntity.setDeletedAt(LocalDateTime.now());
			// get AuditorAware from Spring context
	        @SuppressWarnings("unchecked")
			AuditorAware<String> auditorAware = SpringContext.getBean(AuditorAware.class);
	        softDeletableEntity.setDeletedBy(auditorAware.getCurrentAuditor().orElse("Unknown"));
			super.save(entity);
		} else {
			super.delete(entity);
		}
	}
}
