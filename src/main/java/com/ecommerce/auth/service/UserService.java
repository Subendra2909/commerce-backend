package com.ecommerce.auth.service;

import com.ecommerce.auth.dto.response.UserResponse;

public interface UserService {

    UserResponse getCurrentUser(String email);
}
