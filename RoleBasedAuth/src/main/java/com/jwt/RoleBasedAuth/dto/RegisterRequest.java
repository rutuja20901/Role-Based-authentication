package com.jwt.RoleBasedAuth.dto;

import com.jwt.RoleBasedAuth.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {

    private String username;

    private String email;

    private String password;

    private Role role;

}
