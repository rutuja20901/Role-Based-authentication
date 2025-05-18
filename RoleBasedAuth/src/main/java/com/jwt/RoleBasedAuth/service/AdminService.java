package com.jwt.RoleBasedAuth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.jwt.RoleBasedAuth.entity.Role;
import com.jwt.RoleBasedAuth.entity.UserEntity;
import com.jwt.RoleBasedAuth.repository.AdminRepo;

@Service
public class AdminService {

    @Autowired
    private AdminRepo adminRepo;

    // // get data
    // public ResponseEntity<Object> getData() {
    // List<UserEntity> l = adminRepo.findAll();
    // if (l.isEmpty()) {
    // return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    // }
    // return ResponseEntity.status(HttpStatus.OK).body(l);

    // }

    // List of admin
    public List<UserEntity> getDataByAdmin(Role role) {
        return adminRepo.findByRole(role);
    }

    // list of users
    public ResponseEntity<Object> getAllAdmin() {
        List<UserEntity> l = adminRepo.findAll();
        if (l.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(l);
        }
    }

    // Delete AdminBy Id
    public void deleteAdmin(Long id) {
        adminRepo.deleteById(id);
    }

    // Update Admin by id
    public ResponseEntity<Object> updateAdmin(@RequestBody UserEntity user, @PathVariable Long id) {
        Optional<UserEntity> u = adminRepo.findById(id);
        if (u.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            UserEntity m = u.get();
            m.setEmail(user.getEmail());
            m.setUsername(user.getUsername());
            UserEntity a = adminRepo.save(m);
            return ResponseEntity.status(HttpStatus.OK).body(a);
        }
    }

}
