package com.eazybytes.accounts.utils;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.eazybytes.accounts.dto.AccountRequest;
import com.eazybytes.accounts.dto.AccountResponse;
import com.eazybytes.accounts.model.Account;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AccountMapper {	
	void mapAccountFromRequest(AccountRequest source, @MappingTarget Account target);
	void mapAccountToResponse(Account source, @MappingTarget AccountResponse target);
}
