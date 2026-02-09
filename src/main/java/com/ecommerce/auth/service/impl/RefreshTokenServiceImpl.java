package com.ecommerce.auth.service.impl;

import com.ecommerce.auth.entity.RefreshToken;
import com.ecommerce.auth.repository.RefreshTokenRepository;
import com.ecommerce.auth.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    private final long REFRESH_EXPIRY_DAYS = 7;

    @Override
    public String createRefreshToken(String email) {

        refreshTokenRepository.deleteByUserEmail(email);

        String token = java.util.UUID.randomUUID().toString();

        RefreshToken refreshToken = RefreshToken.builder()
                .token(token)
                .userEmail(email)
                .expiryDate(LocalDateTime.now().plusDays(REFRESH_EXPIRY_DAYS))
                .build();

        refreshTokenRepository.save(refreshToken);

        return token;
    }

    @Override
    public String validateAndGetEmail(String token) {

        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Refresh token expired");
        }

        return refreshToken.getUserEmail();
    }

    @Override
    public void revokeByToken(String token) {

        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        refreshTokenRepository.delete(refreshToken);
    }

}

