package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.UserRequest;
import com.eazybytes.accounts.dto.UserResponse;

public interface UserService {
    UserResponse createUser(UserRequest request);
}
