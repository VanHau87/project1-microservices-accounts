package com.eazybytes.accounts.utils;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.model.Customer;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {
	void mapCustomerFromDto(CustomerDto source, @MappingTarget Customer target);
	void mapCustomerToDto(Customer source, @MappingTarget CustomerDto target);
}
