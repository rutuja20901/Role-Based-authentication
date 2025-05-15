package com.jwt.RoleBasedAuth.repository;

import java.util.List;

import com.jwt.RoleBasedAuth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.jwt.RoleBasedAuth.entity.UserEntity;

@Repository
public interface AdminRepo extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findByRole(com.jwt.RoleBasedAuth.entity.Role admin);

    ResponseEntity<Object> deleteAdminId(UserEntity id);

}
