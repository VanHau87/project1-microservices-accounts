package com.eazybytes.accounts.utils;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.eazybytes.accounts.dto.AccountDto;
import com.eazybytes.accounts.model.Account;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AccountMapper {	
	void mapAccountFromDto(AccountDto source, @MappingTarget Account target);
	void mapAccountToDto(Account source, @MappingTarget AccountDto target);
}
