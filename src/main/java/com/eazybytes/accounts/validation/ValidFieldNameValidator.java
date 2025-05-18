package com.eazybytes.accounts.validation;

import java.util.Arrays;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidFieldNameValidator implements ConstraintValidator<ValidFieldName, String> {

	private Class<?> entityClass;

    @Override
    public void initialize(ValidFieldName constraintAnnotation) {
        this.entityClass = constraintAnnotation.entity();
    }

    @Override
    public boolean isValid(String fieldName, ConstraintValidatorContext context) {
        if (fieldName == null || fieldName.isBlank()) return true;

        return Arrays.stream(entityClass.getDeclaredFields())
                     .anyMatch(f -> f.getName().equals(fieldName));
    }

}
