package com.eazybytes.accounts.utils;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.eazybytes.accounts.dto.UserRequest;
import com.eazybytes.accounts.dto.UserResponse;
import com.eazybytes.accounts.model.User;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
	User mapUserFromRequest(UserRequest source, @MappingTarget User target);
	UserResponse mapUserToResponse(User source, @MappingTarget UserResponse target);
}
