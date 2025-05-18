package com.eazybytes.accounts.validation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueFieldValidator implements ConstraintValidator<UniqueField, Object> {
	private String fieldName;
    private Class<?> entityClass;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void initialize(UniqueField constraintAnnotation) {
        fieldName = constraintAnnotation.field();
        entityClass = constraintAnnotation.entity();
    }
    @Override
    public boolean isValid(Object fieldValue, ConstraintValidatorContext context) {
        if (fieldValue == null) {
            return true;
        }

        String queryStr = "SELECT COUNT(e) FROM " + entityClass.getSimpleName() +
                " e WHERE e." + fieldName + " = :fieldValue";

        Long count = entityManager.createQuery(queryStr, Long.class)
                .setParameter("fieldValue", fieldValue)
                .getSingleResult();

        return count == 0;
    }
}
