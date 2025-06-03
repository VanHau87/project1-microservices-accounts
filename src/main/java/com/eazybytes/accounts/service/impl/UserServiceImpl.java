package com.eazybytes.accounts.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eazybytes.accounts.dto.UserRequest;
import com.eazybytes.accounts.dto.UserResponse;
import com.eazybytes.accounts.model.User;
import com.eazybytes.accounts.repository.UserRepository;
import com.eazybytes.accounts.service.UserService;
import com.eazybytes.accounts.utils.UserMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    
    public UserResponse createUser(UserRequest request) {
        if (userRepository.findByUsernameOrEmail(request.getUsername(), request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Username or Email already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(true);
        user.setMobileNumber(request.getMobileNumber());
        user.setCustomerType(request.getCustomerType());
        user.setStatus(request.getStatus());

        User savedUser = userRepository.save(user);
        return userMapper.mapUserToResponse(savedUser, new UserResponse());
    }
}
