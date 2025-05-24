package com.eazybytes.accounts.utils;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.eazybytes.accounts.dto.CustomerRequest;
import com.eazybytes.accounts.dto.CustomerResponse;
import com.eazybytes.accounts.model.Customer;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {
	void mapCustomerFromRequest(CustomerRequest source, @MappingTarget Customer target);
	void mapCustomerToResponse(Customer source, @MappingTarget CustomerResponse target);
}
