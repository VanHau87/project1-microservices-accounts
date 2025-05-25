package com.eazybytes.accounts.validation;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanWrapperImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueFieldValidator implements ConstraintValidator<UniqueField, Object> {
	private String[] fields;
    private Class<?> entityClass;
    private String idField;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void initialize(UniqueField constraintAnnotation) {
        this.fields = constraintAnnotation.fields();
        this.entityClass = constraintAnnotation.entity();
        this.idField = constraintAnnotation.idField();
    }
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
    	if (value == null) return true;

        Set<String> duplicatedFields = new HashSet<>();
        Object idValue = getPropertyValue(value, idField);
        for (String field : fields) {
            Object fieldValue = getPropertyValue(value, field);
            if (fieldValue == null || fieldValue.toString().isBlank()) continue;

            // Chỉ check trùng, nếu là insert thì idValue sẽ là null
            String jpql = "SELECT COUNT(e) FROM " + entityClass.getSimpleName() +
                          " e WHERE e." + field + " = :fieldValue";
            if (idValue != null) {
                jpql += " AND e." + idField + " <> :idValue";
            }

            var query = entityManager.createQuery(jpql, Long.class)
                                     .setParameter("fieldValue", fieldValue);

            if (idValue != null) {
                query.setParameter("idValue", idValue);
            }

            Long count = query.getSingleResult();
            if (count > 0) {
                duplicatedFields.add(field);
            }
        }
        // Nếu có field trùng, thêm violation cho từng field
        if (!duplicatedFields.isEmpty()) {
            context.disableDefaultConstraintViolation();
            for (String field : duplicatedFields) {
                context.buildConstraintViolationWithTemplate(field + " must be unique")
                        .addPropertyNode(field)
                        .addConstraintViolation();
            }
            return false;
        }
        return true;
    }
    private Object getPropertyValue(Object bean, String property) {
        try {
            return new BeanWrapperImpl(bean).getPropertyValue(property);
        } catch (Exception e) {
            return null;
        }
    }
}
