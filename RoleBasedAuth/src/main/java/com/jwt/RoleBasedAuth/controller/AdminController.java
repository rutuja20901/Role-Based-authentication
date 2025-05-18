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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.RoleBasedAuth.entity.Role;
import com.jwt.RoleBasedAuth.entity.UserEntity;
import com.jwt.RoleBasedAuth.service.AdminService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/role")
    public ResponseEntity<List<UserEntity>> addData(@RequestParam String role) {
        return ResponseEntity.ok(adminService.getDataByAdmin(Role.valueOf(role.toUpperCase())));
    }

    @GetMapping("/get-admin")
    public ResponseEntity<Object> getData() {
        return ResponseEntity.ok(adminService.getAllAdmin());
    }

    @DeleteMapping("/delete-admin/{id}")
    public ResponseEntity<Object> deleteData(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.ok("User deleted successfully!");
    }

    @PutMapping("/update-admin/{id}")
    public ResponseEntity<Object> updateData(@PathVariable Long id, @RequestBody UserEntity entity) {
        adminService.updateAdmin(entity, id);
        return ResponseEntity.ok("User updated sucessfully!");
    }

}
