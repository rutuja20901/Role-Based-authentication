package com.jwt.RoleBasedAuth.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.RoleBasedAuth.entity.UserEntity;
import com.jwt.RoleBasedAuth.repository.UserRepo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        // UserEntity user = userRepo.findByUsername(username);
        // if (user == null) {
        // throw new UsernameNotFoundException("username is not found");
        // }

        // Set ROLE_ prefix so that @PreAuthorize("hasRole('ADMIN')") works
        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                // Collections.singleton(new SimpleGrantedAuthority(user.getRole().name()))
                authorities);

    }

}
