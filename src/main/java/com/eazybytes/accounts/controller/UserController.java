package com.eazybytes.accounts.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.accounts.dto.UserRequest;
import com.eazybytes.accounts.dto.UserResponse;
import com.eazybytes.accounts.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	@PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRequest userDto) {
        UserResponse createdUser = userService.createUser(userDto);
        return ResponseEntity.ok(createdUser);
    }
}
