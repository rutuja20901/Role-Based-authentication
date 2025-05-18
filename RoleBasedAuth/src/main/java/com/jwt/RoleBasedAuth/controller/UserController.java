package com.jwt.RoleBasedAuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.RoleBasedAuth.entity.UserEntity;
import com.jwt.RoleBasedAuth.service.UsersService;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class UserController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/get-user")
    public ResponseEntity<Object> getData() {
        return usersService.gettAllUser();
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<Object> deleteData(@PathVariable Long id) {
        usersService.deleteUserById(id);
        return ResponseEntity.ok("User Deleted successfully!");
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<Object> updateData(@RequestBody UserEntity user, @PathVariable Long id) {
        usersService.updateById(user, id);
        return ResponseEntity.ok("User updated successfully!");
    }

}
