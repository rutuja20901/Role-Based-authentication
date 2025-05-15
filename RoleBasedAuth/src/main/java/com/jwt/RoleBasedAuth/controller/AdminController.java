package com.jwt.RoleBasedAuth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.RoleBasedAuth.entity.Role;
import com.jwt.RoleBasedAuth.entity.UserEntity;
import com.jwt.RoleBasedAuth.service.AdminService;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // @GetMapping("/get")
    // public ResponseEntity<Object> getData() {
    // return adminService.getData();
    // }

    @GetMapping("/dashboard")
    public ResponseEntity<String> getAdminData() {
        return ResponseEntity.ok("Welcome Admin!");
    }

    @GetMapping("/add-admin")
    public ResponseEntity<List<UserEntity>> addData() {
        return ResponseEntity.ok(adminService.getDataByAdmin(Role.ADMIN));
    }

    @DeleteMapping("/delete-admin/{id}")
    public ResponseEntity<Object> deleteData(@RequestBody UserEntity id) {
        return ResponseEntity.ok(adminService.deleteAdmin(id));
    }

}
