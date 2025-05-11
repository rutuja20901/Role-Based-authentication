package com.jwt.RoleBasedAuth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.RoleBasedAuth.dto.LoginRequest;
import com.jwt.RoleBasedAuth.dto.RegisterRequest;
import com.jwt.RoleBasedAuth.entity.UserEntity;
import com.jwt.RoleBasedAuth.repository.UserRepo;
import com.jwt.RoleBasedAuth.security.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String register(RegisterRequest request) {
        if (userRepo.existsByUsername(request.getUsername())) {
            return "User already exists!";
        }
        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepo.save(user);
        return "User Register successfully";
    }

    public String login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())

        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtUtil.generateToken(((UserDetails) authentication.getPrincipal()).getUsername());
    }

}
