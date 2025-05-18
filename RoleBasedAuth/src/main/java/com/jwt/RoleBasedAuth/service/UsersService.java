package com.jwt.RoleBasedAuth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.jwt.RoleBasedAuth.entity.UserEntity;
import com.jwt.RoleBasedAuth.repository.UserRepo;

@Service
public class UsersService {

    @Autowired
    private UserRepo userRepo;

    // Get all users
    public ResponseEntity<Object> gettAllUser() {
        List<UserEntity> l = userRepo.findAll();
        if (l.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(l);
        }
    }

    // Delete users
    public void deleteUserById(Long id) {
        userRepo.deleteById(id);
    }

    // Update by id
    public ResponseEntity<Object> updateById(UserEntity user, Long id) {
        Optional<UserEntity> l = userRepo.findById(id);
        if (l.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            UserEntity u = l.get();
            u.setEmail(user.getEmail());
            u.setUsername(user.getUsername());
            UserEntity m = userRepo.save(u);
            return ResponseEntity.status(HttpStatus.OK).body(m);
        }
    }

}
