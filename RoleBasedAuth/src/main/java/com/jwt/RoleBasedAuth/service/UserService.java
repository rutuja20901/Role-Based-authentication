package com.jwt.RoleBasedAuth.service;

import com.jwt.RoleBasedAuth.dto.LoginRequest;
import com.jwt.RoleBasedAuth.dto.LoginResponse;
import com.jwt.RoleBasedAuth.dto.RegisterRequest;

public interface UserService {
    LoginResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

}
