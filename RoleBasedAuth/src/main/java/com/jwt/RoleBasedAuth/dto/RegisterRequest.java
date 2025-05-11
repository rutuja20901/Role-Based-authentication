package com.jwt.RoleBasedAuth.dto;

import com.jwt.RoleBasedAuth.entity.Role;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String username;

    private String email;

    private String password;

    private Role role;

}
