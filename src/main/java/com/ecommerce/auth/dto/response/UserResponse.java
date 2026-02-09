package com.ecommerce.auth.dto.response;

import com.ecommerce.auth.entity.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {

    private String id;
    private String email;
    private Role role;
}
