package com.ecommerce.auth.service;

public interface RefreshTokenService {

    String createRefreshToken(String email);

    String validateAndGetEmail(String token);
    void revokeByToken(String token);
}

