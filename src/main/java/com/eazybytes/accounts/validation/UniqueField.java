package com.eazybytes.accounts.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueFieldValidator.class)
public @interface UniqueField {
	String message() default "This value already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

 // Các field cần check unique (tên thuộc tính của entity)
    String[] fields();
    // Entity class
    Class<?> entity();
    // Tên trường id entity, mặc định "id"
    String idField() default "id";
}
