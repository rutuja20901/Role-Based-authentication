package com.jwt.RoleBasedAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.RoleBasedAuth.service.AdminService;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/get")
    public ResponseEntity<Object> getData() {
        return adminService.getData();
    }

    @GetMapping("/dashboard")
    public ResponseEntity<String> getAdminData() {
        return ResponseEntity.ok("Welcome Admin!");
    }

}
