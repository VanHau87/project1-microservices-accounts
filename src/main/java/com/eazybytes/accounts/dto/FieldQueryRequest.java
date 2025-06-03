package com.eazybytes.accounts.dto;

import com.eazybytes.accounts.model.User;
import com.eazybytes.accounts.validation.ValidFieldName;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldQueryRequest {
	@NotBlank(message = "Field name must not be blank")
	@ValidFieldName(entity = User.class)
    private String fieldName;

    @NotBlank(message = "Field value must not be blank")
    private String value;
}
