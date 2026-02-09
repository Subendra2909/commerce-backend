package com.ecommerce.auth.service.impl;

import com.ecommerce.auth.dto.response.UserResponse;
import com.ecommerce.auth.entity.User;
import com.ecommerce.auth.exception.InvalidCredentialsException;
import com.ecommerce.auth.repository.UserRepository;
import com.ecommerce.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse getCurrentUser(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidCredentialsException("User not found"));

        return UserResponse.builder()
                .id(user.getId().toString())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
