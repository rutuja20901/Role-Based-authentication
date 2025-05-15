package com.jwt.RoleBasedAuth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    public List<UserEntity> getDataByAdmin(Role admin) {
        return adminRepo.findByRole(admin);
    }

    // Delete UserBy Id
    public ResponseEntity<Object> deleteAdmin(UserEntity id) {
        return adminRepo.deleteAdminId(id);
    }

}
